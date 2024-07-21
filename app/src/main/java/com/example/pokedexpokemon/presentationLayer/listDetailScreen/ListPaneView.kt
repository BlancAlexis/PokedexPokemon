package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.DetailsPokemonScreen
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon.ExtraCardScreen
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon.ListPokemonScreen

@Composable
fun ListDetailsHost(
     viewModel: ListDetailPokemonViewModel,
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    ListDetailLayout(uiState = uiState, onEvent = viewModel)
}
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(
    modifier: Modifier = Modifier, uiState: List<ListDetailsPokemonUiState>, onEvent: () -> Unit = {}
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(modifier = modifier,
        navigator = navigator,
        listPane = @Composable {
            AnimatedPane {
                ListPokemonScreen(uiState = uiState, onNavigate = {
                    navigator.navigateTo(
                        pane = ListDetailPaneScaffoldRole.Detail,
                        content = "Item ")
                })
            }
        },
        detailPane = @Composable {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an item"
            AnimatedPane {
                DetailsPokemonScreen(uiState)
            }
        },
        extraPane = @Composable {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an option"
            AnimatedPane {
                ExtraCardScreen()
            }
        })
}