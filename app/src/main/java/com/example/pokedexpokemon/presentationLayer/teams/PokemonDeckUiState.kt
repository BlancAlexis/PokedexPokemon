package com.example.pokedexpokemon.presentationLayer.teams

data class DeckUiState(
    val id: Int, val name: String, val cards: List<PokemonCardUiState>
)

data class PokemonCardUiState(
    val id: Int, val name: String, val number: String
)
