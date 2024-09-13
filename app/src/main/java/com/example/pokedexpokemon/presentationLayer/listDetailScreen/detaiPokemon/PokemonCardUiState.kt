package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import com.example.pokedexpokemon.domainLayer.model.CardPrice
import com.example.pokedexpokemon.presentationLayer.util.SealedPokemonType
import java.io.Serializable

data class CardPokemonUiState(
    val image: String,
    val name: String,
    val subtypes: List<SealedPokemonType>,
    val types: List<String>,
    val number: String,
    val artist: String,
    val rarity: String,
    val cardPrice: CardPrice
) : Serializable
