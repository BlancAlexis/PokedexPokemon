package com.example.pokedexpokemon.dataLayer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokedexpokemon.dataLayer.entity.CardEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckEntity
import com.example.pokedexpokemon.dataLayer.room.DeckWithPokemonCardMapper
import kotlinx.coroutines.flow.Flow
@Dao
interface DeckDAO {
    @Insert
    suspend fun insertDeck(deck: DeckEntity): Long

    @Delete
    suspend fun deleteDeck(deck: DeckEntity)
    @Insert
    suspend fun insertCard(card: CardEntity): Long

    @Transaction
    suspend fun insertDeckWithCards(deck: DeckEntity, cards: List<CardEntity>) {
        val deckId = insertDeck(deck)
        cards.forEach { card ->
            insertCard(card.copy(deckId = deckId))
        }
    }

    @Query("DELETE FROM deckEntity WHERE deckId = :deckId")
    suspend fun deleteDeckById(deckId: Long)

    @Transaction
    @Query("SELECT * FROM deckEntity WHERE deckId= :deckId")
    fun getDeckWithCardsById(deckId: Long): Flow<DeckWithPokemonCardMapper>

    @Transaction
    @Query("SELECT * FROM deckEntity ")
    fun getDeckWithCards(): Flow<List<DeckWithPokemonCardMapper>>
}

