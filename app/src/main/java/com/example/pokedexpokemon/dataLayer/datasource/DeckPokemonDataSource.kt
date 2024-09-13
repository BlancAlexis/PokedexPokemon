package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.dao.DeckDAO
import com.example.pokedexpokemon.dataLayer.datasource.mapper.toEntity
import com.example.pokedexpokemon.dataLayer.entity.CardEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckEntity
import com.example.pokedexpokemon.dataLayer.room.Card
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.room.DeckWithPokemonCardMapper
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent


interface DeckPokemonDataSource {
     suspend fun getAllDeckPokemonUseCase(): Flow<List<DeckWithPokemonCardMapper>>
    suspend fun insertDeckPokemonUseCase(deck: DeckWithPokemonCardMapper)
    suspend fun removeDeckPokemonUseCase(index: String)
    fun addCardToDeckPokemonUseCase(index: String)
    fun removeCardFromDeckPokemonUseCase(index: String)
}

class DeckPokemonDataSourceImpl(
    val deckPokemonDao: DeckDAO,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DeckPokemonDataSource {
    override suspend fun getAllDeckPokemonUseCase(): Flow<List<DeckWithPokemonCardMapper>> {
        return withContext(dispatcher) {
             deckPokemonDao.getDeckWithCards()
        }
    }

    suspend override fun insertDeckPokemonUseCase(deck: DeckWithPokemonCardMapper)  {
         withContext(dispatcher) {
             deckPokemonDao.insertDeckWithCards(deck.deck, deck.cards)
          //  deckPokemonDao.insertDeck(deck)
        }
    }

    suspend override fun removeDeckPokemonUseCase(index: String) {
        TODO("Not yet implemented")
    }

    override fun addCardToDeckPokemonUseCase(index: String) {
        TODO("Not yet implemented")
    }

    override fun removeCardFromDeckPokemonUseCase(index: String) {
        TODO("Not yet implemented")
    }
}

interface DeckPokemonRepository {
    suspend fun getAllDeckPokemonUseCase(): Flow<List<Deck>>
    suspend fun insertDeckPokemonUseCase(deck: Deck)
    suspend fun removeDeckPokemonUseCase(index: String)
     fun addCardToDeckPokemonUseCase(index: String)
     fun removeCardFromDeckPokemonUseCase(index: String)
}

class DeckPokemonRepositoryImpl(
    val deckPokemonDataSource: DeckPokemonDataSource
) : DeckPokemonRepository {
    override suspend fun getAllDeckPokemonUseCase(): Flow<List<Deck>> {
        return deckPokemonDataSource.getAllDeckPokemonUseCase().map { it.map { Deck(  name =  it.deck.name, cards =  it.cards.map { Card(name =  it.name, rarity =  it.image) }) } }
    }

    override suspend fun insertDeckPokemonUseCase(deck: Deck)  {
         deckPokemonDataSource.insertDeckPokemonUseCase(deck.toEntity())
    }

    override suspend fun removeDeckPokemonUseCase(index: String) {
        TODO("Not yet implemented")
    }

    override fun addCardToDeckPokemonUseCase(index: String) {
        TODO("Not yet implemented")
    }

    override fun removeCardFromDeckPokemonUseCase(index: String) {
        TODO("Not yet implemented")
    }
}

class GetAllDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {

    suspend fun invoke(): Flow<Ressource<List<Deck>>>  {
        return try {
            deckPokemonRepository.getAllDeckPokemonUseCase().map { Ressource.Success(it) }
        } catch (e: Exception) {
            flowOf(Ressource.Error(exception = e))
        }
    }

}
class InsertDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(deck: Deck): Ressource<Unit> {
        return try {
            deckPokemonRepository.insertDeckPokemonUseCase(deck)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}
class DeleteDeckPokemonUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<Unit> {
        return try {
            deckPokemonRepository.removeDeckPokemonUseCase(index)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}


object mapper{
    fun Deck.toEntity() = DeckWithPokemonCardMapper(
        deck = DeckEntity(name = name),
        cards = cards.map { it.toEntity() }
    )
    fun DeckWithPokemonCardMapper.toDomain() = Deck(
        name = deck.name,
        cards = cards.map { it.toDomain() }
    )
fun Card.toEntity() = CardEntity(
    name = name,
    image = rarity,
    deckId = 0 //TODO
)
fun CardEntity.toDomain() = Card(
    name = name,
    rarity = image
)
}
/*
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

}*/
