package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.dataLayer.ListDetailsState
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
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    ListDetailLayout(state = state, navigationEvent = { viewModel })
}
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(
    modifier: Modifier = Modifier, state: ListDetailsState, navigationEvent: (NavigationEvent) -> Unit = {}
) {
        AnimatedVisibility(visible = state is ListDetailsState.Loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator()
        }
    }
    AnimatedVisibility(visible = state is ListDetailsState.onFirstSalveLoad, enter = slideInHorizontally(), exit = slideOutHorizontally()) {
        val uiState = state as ListDetailsState.onFirstSalveLoad
        val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
        NavigableListDetailPaneScaffold(modifier = modifier,
            navigator = navigator,
            listPane = @Composable {
                AnimatedPane {
                    ListPokemonScreen(uiState = uiState, onNavigate = { index ->
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Detail,
                            content = uiState.uiStates[index])
                    })
                }
            },
            detailPane = @Composable {
                val content = navigator.currentDestination?.content as? ListDetailsPokemonUiState
                AnimatedPane {
                    DetailsPokemonScreen(uiState = content ?: state.uiStates[0], onNavigate = {
                        navigator.navigateTo(
                            pane = ListDetailPaneScaffoldRole.Extra)
                    })
                }
            },
            extraPane = @Composable {
               // val content = navigator.currentDestination?.content?.toString() ?: "Select an option"
                AnimatedPane {
                    ExtraCardScreen()
                }
            })
    }

}