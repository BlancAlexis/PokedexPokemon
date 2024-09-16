package com.example.pokedexpokemon.domainLayer.usecase

import com.example.pokedexpokemon.dataLayer.datasource.DeckPokemonRepository
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent

class SaveDeckUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(deck: Deck): Ressource<Unit> {
        return try {
            Ressource.Success(deckPokemonRepository.insertDeckPokemonUseCase(deck = deck))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }
}