package com.example.pokedexpokemon.presentationLayer.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsHost(
    viewModel: SettingsViewModel = koinViewModel()
){
    SettingsView()
}
@Composable
fun SettingsView (

){
    Column(Modifier.fillMaxSize()) {
        Text(text = "Settings")
    }
}