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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeck(deck: DeckEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

    @Delete
    suspend fun deleteDeck(deck: DeckEntity)

    @Delete
    suspend fun deleteCard(card: CardEntity)

    @Transaction
    suspend fun insertDeckWithCards(deck: DeckEntity, cards: List<CardEntity>) {
        insertDeck(deck)
        cards.forEach { card ->
            insertCard(card.copy(deckId = deck.deckId))
        }
    }

    @Transaction
    @Query("SELECT * FROM deckEntity")fun getDeckWithCards(): Flow<List<DeckWithPokemonCardMapper>>

}