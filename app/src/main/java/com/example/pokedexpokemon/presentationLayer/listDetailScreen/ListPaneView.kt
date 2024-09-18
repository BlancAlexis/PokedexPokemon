package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
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
    state: List<ListDetailsPokemonUiState>,
    navigationEvent: (NavigationEvent) -> Unit = {},
    viewModelEvent: (ListDetailsPokemonEvent) -> Unit = {}
) {

    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
        NavigableListDetailPaneScaffold(modifier = Modifier.padding(top = 20.dp),
            navigator = navigator,
            listPane = @Composable {
                AnimatedPane {
                    ListPokemonScreen(
                        uiState = state, onNavigate = { index ->
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                content = state[index]

                            )
                        },
                        viewModelEvent = viewModelEvent
                    )
                }
            },
            detailPane = @Composable {
                val content = if (navigator.currentDestination?.content == null) {
                    if (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                        state.firstOrNull()
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