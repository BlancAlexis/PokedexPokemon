package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent

interface DeckPokemonDataSource {}

class DeckPokemonDataSourceImpl : DeckPokemonDataSource {}

interface DeckPokemonRepository {}

class DeckPokemonRepositoryImpl : DeckPokemonRepository {}

class GetAllDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<BasePokemon> {
        return try {
            Ressource.Success(basePokemonRepository.getPokemon(index))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}
class InsertDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<BasePokemon> {
        return try {
            Ressource.Success(basePokemonRepository.getPokemon(index))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}
class DeleteDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<BasePokemon> {
        return try {
            Ressource.Success(basePokemonRepository.getPokemon(index))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}
class AddCardToDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<BasePokemon> {
        return try {
            Ressource.Success(basePokemonRepository.getPokemon(index))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}class RemoveCardFromDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<BasePokemon> {
        return try {
            Ressource.Success(basePokemonRepository.getPokemon(index))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}