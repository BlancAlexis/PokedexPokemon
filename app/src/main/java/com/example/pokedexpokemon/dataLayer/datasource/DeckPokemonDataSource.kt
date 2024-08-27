package com.example.pokedexpokemon.dataLayer.datasource

import BasePokemon
import com.example.pokedexpokemon.dataLayer.dao.DeckDAO
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.room.DeckWithPokemonCardMapper
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent


interface DeckPokemonDataSource {
    fun getAllDeckPokemonUseCase(index: String): Flow<List<DeckWithPokemonCardMapper>>
    fun insertDeckPokemonUseCase(index: String): Ressource<Unit>
    fun removeDeckPokemonUseCase(index: String): Ressource<Unit>
    fun addCardToDeckPokemonUseCase(index: String): Ressource<Unit>
    fun removeCardFromDeckPokemonUseCase(index: String): Ressource<Unit>
}

class DeckPokemonDataSourceImpl(
    val deckPokemonDao: DeckDAO
) : DeckPokemonDataSource {
    override fun getAllDeckPokemonUseCase(index: String): Flow<List<DeckWithPokemonCardMapper>> {
        TODO("Not yet implemented")
    }

    override fun insertDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun removeDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun addCardToDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun removeCardFromDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }
}

interface DeckPokemonRepository {
    fun getAllDeckPokemonUseCase(index: String): Flow<List<Deck>>
     fun insertDeckPokemonUseCase(index: String): Ressource<Unit>
     fun removeDeckPokemonUseCase(index: String): Ressource<Unit>
     fun addCardToDeckPokemonUseCase(index: String): Ressource<Unit>
     fun removeCardFromDeckPokemonUseCase(index: String): Ressource<Unit>
}

class DeckPokemonRepositoryImpl(
    val deckPokemonDataSource: DeckPokemonDataSource
) : DeckPokemonRepository {
    override fun getAllDeckPokemonUseCase(index: String): Flow<List<Deck>> {
        TODO("Not yet implemented")
    }

    override fun insertDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun removeDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun addCardToDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }

    override fun removeCardFromDeckPokemonUseCase(index: String): Ressource<Unit> {
        TODO("Not yet implemented")
    }
}

class GetAllDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<BasePokemon> {
        return try {
            Ressource.Success(deckPokemonRepository.ge(index))
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

}
class RemoveCardFromDeckPokemonUseCase(
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