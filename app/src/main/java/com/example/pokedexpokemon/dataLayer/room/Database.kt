package com.example.pokedexpokemon.dataLayer.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexpokemon.dataLayer.dao.DeckDAO
import com.example.pokedexpokemon.dataLayer.entity.CardEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckEntity

@Database(
    entities = [CardEntity::class, DeckEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class Database : RoomDatabase() {
    abstract fun getDeckDAO(): DeckDAO
}