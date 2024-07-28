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
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.NavigationEvent
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.DetailsPokemonScreen
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon.ExtraCardHost
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon.ListPokemonScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListDetailsHost(
    viewModel: ListDetailPokemonViewModel = koinViewModel(),
    navigationEvent: (NavigationEvent) -> Unit = {},
    modifier: Modifier
) {
    val state = viewModel.uiState.collectAsLazyPagingItems()
    ListDetailLayout(
        modifier = modifier,
        state = state,
        navigationEvent = { viewModel },
        viewModelEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(
    modifier: Modifier = Modifier,
    state: LazyPagingItems<ListDetailsPokemonUiState>,
    navigationEvent: (NavigationEvent) -> Unit = {},
    viewModelEvent: (ListDetailsPokemonEvent) -> Unit = {}
) {
    AnimatedVisibility(visible = state.itemCount == 0) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    AnimatedVisibility(
        visible = state.itemCount > 0,
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
                                content = {
                                    index
                                }
                            )
                        },
                        viewModelEvent = viewModelEvent
                    )
                }
            },
            detailPane = @Composable {
                val content = navigator.currentDestination?.content as? Int
                AnimatedPane {
                 DetailsPokemonScreen(uiState = if (content == null){state.itemSnapshotList.items[0]} else {state.itemSnapshotList.items[content]}  , onNavigate = {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Extra
                            )
                        })
                    }
            },
            extraPane = @Composable {
                // val content = navigator.currentDestination?.content?.toString() ?: "Select an option"
                AnimatedPane {
                    ExtraCardHost()
                }
            })
    }

}