package com.example.pokedexpokemon.dataLayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokedexpokemon.dataLayer.entity.CardEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckEntity
import com.example.pokedexpokemon.dataLayer.room.DeckWithPokemonCardMapper

@Dao
interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeck(deck: DeckEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDeckCardCrossRef(deckCardCrossRef: DeckWithPokemonCard)

    @Transaction
    @Query("SELECT * FROM deckEntity")
    suspend fun getDeckWithCards(): DeckWithPokemonCardMapper

}