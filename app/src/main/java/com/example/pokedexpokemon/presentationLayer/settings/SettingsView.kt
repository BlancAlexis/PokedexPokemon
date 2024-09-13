package com.example.pokedexpokemon.presentationLayer.settings

import androidx.compose.runtime.Composable
import com.example.pokedexpokemon.presentationLayer.util.SettingsBackground
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsHost(
    viewModel: SettingsViewModel = koinViewModel()
) {
    SettingsView()
}

@Composable
fun SettingsView(

) {
SettingsBackground()
    }