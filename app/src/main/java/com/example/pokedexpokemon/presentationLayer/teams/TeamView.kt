package com.example.pokedexpokemon.presentationLayer.teams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pokedexpokemon.presentationLayer.util.d
import org.koin.androidx.compose.koinViewModel


@Composable
fun TeamViewHost(
    viewModel: TeamViewModel = koinViewModel()
) {
    TeamView()
}

@Composable
fun TeamView(

) {
    d()
}