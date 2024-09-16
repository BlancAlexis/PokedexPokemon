package com.example.pokedexpokemon.domainLayer.usecase

import com.example.pokedexpokemon.dataLayer.datasource.DeckPokemonRepository
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class GetDeckUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(): Flow<Ressource<List<Deck>>> {
        return try {
            deckPokemonRepository.getAllDeckPokemonUseCase().map {
                Ressource.Success(it)
            }
        } catch (e: Exception) {
            flowOf(Ressource.Error(exception = e))
        }
    }

}

