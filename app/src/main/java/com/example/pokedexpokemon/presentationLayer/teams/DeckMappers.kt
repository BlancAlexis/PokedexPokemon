package com.example.pokedexpokemon.presentationLayer.teams

import com.example.pokedexpokemon.dataLayer.room.Card
import com.example.pokedexpokemon.dataLayer.room.Deck

object DeckMappers {
    fun Deck.toUiState(): DeckUiState {
        return DeckUiState(id = id ?: 0, name = name, cards = cards.map { it.toUiState() })
    }

    fun Card.toUiState(): PokemonCardUiState {
        return PokemonCardUiState(
            id = id ?: 0, name = name, number = ""
        )
    }
}