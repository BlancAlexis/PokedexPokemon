package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.dataLayer.ListDetailsUiState
import com.example.pokedexpokemon.presentationLayer.NavigationEvent
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.DetailsPokemonScreen
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon.ExtraCardScreen
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon.ListPokemonScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListDetailsHost(
    viewModel: ListDetailPokemonViewModel = koinViewModel(),
    navigationEvent: (NavigationEvent) -> Unit = {},
    modifier: Modifier
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    ListDetailLayout(uiState = uiState, navigationEvent = { viewModel })
}
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(
    modifier: Modifier = Modifier, uiState: ListDetailsUiState, navigationEvent: (NavigationEvent) -> Unit = {}
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(modifier = modifier,
        navigator = navigator,
        listPane = @Composable {
            AnimatedPane {
                ListPokemonScreen(uiState = uiState, onNavigate = { index ->
                    navigator.navigateTo(
                        pane = ListDetailPaneScaffoldRole.Detail,
                        content = uiState.list[index])
                })
            }
        },
        detailPane = @Composable {
            val content = navigator.currentDestination?.content
            AnimatedPane {
                DetailsPokemonScreen(uiState = content as? ListDetailsPokemonUiState, onNavigate = {})
            }
        },
        extraPane = @Composable {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an option"
            AnimatedPane {
                ExtraCardScreen()
            }
        })
}