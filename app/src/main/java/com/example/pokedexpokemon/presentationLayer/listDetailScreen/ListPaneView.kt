package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexpokemon.dataLayer.ListDetailsUiState
import com.example.pokedexpokemon.dataLayer.PokemonUiState
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
    val screenUiState = viewModel.screenUiState.collectAsStateWithLifecycle().value
    ListDetailLayout(
        modifier = modifier,
        state = state,
        screenUiState = screenUiState,
        navigationEvent = navigationEvent,
        viewModelEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(
    modifier: Modifier = Modifier,
    state: ListDetailsUiState,
    screenUiState: HomeUiState,
    navigationEvent: (NavigationEvent) -> Unit = {},
    viewModelEvent: (ListDetailsPokemonEvent) -> Unit = {}
) {
    var detailPaneContent by remember { mutableStateOf<PokemonUiState?>(null) }
    Box(modifier = Modifier.fillMaxSize()) {
        if (screenUiState is HomeUiState.Loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        AnimatedVisibility(
            modifier = Modifier.zIndex(0f),
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
                            modifier = modifier,
                            uiState = state, onNavigate = { index ->
                                detailPaneContent = state.pokemonUiState[index]
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail,
                                    content = state.pokemonUiState[index]

                                )
                            },
                            viewModelEvent = viewModelEvent
                        )
                    }
                },
                detailPane = @Composable {
                    val content = when (navigator.currentDestination?.content) {
                        null -> detailPaneContent ?: state.pokemonUiState.firstOrNull()
                        is PokemonUiState -> navigator.currentDestination?.content as PokemonUiState
                        else -> detailPaneContent
                    }
                    AnimatedPane {
                        DetailsPokemonScreen(
                            uiState = content, onNavigate = { name ->
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Extra,
                                    content = name
                                )
                            },
                            playRoar = {
                                viewModelEvent(ListDetailsPokemonEvent.playRoar(it))
                            },
                            navigationEvent = navigationEvent
                        )
                    }
                },
                extraPane = @Composable {
                    val content = navigator.currentDestination?.content?.toString() ?: ""
                    AnimatedPane {
                        ExtraCardHost(name = content, navigationEvent = navigationEvent)
                    }
                })
        }
    }


}