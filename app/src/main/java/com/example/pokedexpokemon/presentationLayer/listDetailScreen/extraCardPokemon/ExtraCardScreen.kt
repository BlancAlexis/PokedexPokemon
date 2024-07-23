package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedexpokemon.R
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
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(vertical = 20.dp)) {
        items(40){
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Blue)
            ) {
                Image(painter = painterResource(id = R.drawable.pika_splash_screen) , contentDescription = "" )

            }
HorizontalDivider()
        }
    }
}

