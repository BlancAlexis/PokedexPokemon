package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokedexpokemon.R
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.PokemonCardState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExtraCardHost(
    viewModel: CardPokemonViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    ExtraCardScreen(uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraCardScreen(uiState: State<PokemonCardState>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        items(40) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Blue)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pika_splash_screen),
                    contentDescription = ""
                )

            }
            HorizontalDivider()
        }
    }
}

