package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExtraCardHost(
    viewModel: CardPokemonViewModel = koinViewModel()
){
ExtraCardScreen()
}
@Composable
fun ExtraCardScreen(

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "content")
    }
}

