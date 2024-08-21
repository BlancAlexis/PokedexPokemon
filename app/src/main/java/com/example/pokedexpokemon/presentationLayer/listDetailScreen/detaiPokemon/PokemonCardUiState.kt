package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import com.example.pokedexpokemon.domainLayer.model.CardPrice

data class CardPokemonUiState(
    val image: String,
    val name: String,
    val subtypes: List<String>,
    val types: List<String>,
    val number: String,
    val artist: String,
    val rarity: String,
    val cardPrice: CardPrice
)