package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.dataLayer.ListDetailsState
import com.example.pokedexpokemon.presentationLayer.NavigationEvent
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.DetailsPokemonScreen
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon.ExtraCardHost
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon.ListPokemonScreen

@Composable
fun ListDetailsHost(
    viewModel: ListDetailPokemonViewModel,
    navigationEvent: (NavigationEvent) -> Unit = {},
    modifier: Modifier
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    ListDetailLayout(
        modifier = modifier,
        state = state,
        navigationEvent = navigationEvent,
        viewModelEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(
    modifier: Modifier = Modifier,
    state: ListDetailsState,
    navigationEvent: (NavigationEvent) -> Unit = {},
    viewModelEvent: (ListDetailsPokemonEvent) -> Unit = {}
) {
    AnimatedVisibility(visible = state is ListDetailsState.Loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    AnimatedVisibility(
        visible = state is ListDetailsState.onFirstSalveLoad,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        val uiState = state as ListDetailsState.onFirstSalveLoad
        val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
        NavigableListDetailPaneScaffold(modifier = Modifier.padding(top = 20.dp),
            navigator = navigator,
            listPane = @Composable {
                AnimatedPane {
                    ListPokemonScreen(
                        uiState = uiState, onNavigate = { index ->
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                content = uiState.uiStates[index]
                            )
                        },
                        viewModelEvent = viewModelEvent
                    )
                }
            },
            detailPane = @Composable {
                val content = if (navigator.currentDestination?.content == null) {
                    if (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                        uiState.uiStates[0]
                    } else {
                        null
                    }
                } else {
                    navigator.currentDestination?.content as? ListDetailsPokemonUiState
                }
                AnimatedPane {
                    DetailsPokemonScreen(
                        uiState = content, onNavigate = { name ->
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Extra,
                                content = name
                            )
                        },
                        playRoar = { it ->
                            viewModelEvent(ListDetailsPokemonEvent.playRoar(it))
                        },
                        navigaionEvent = navigationEvent
                    )
                }
            },
            extraPane = @Composable {
                val content = navigator.currentDestination?.content?.toString() ?: ""
                AnimatedPane {
                    ExtraCardHost(name = content, navigaionEvent = navigationEvent)
                }
            })
    }

}