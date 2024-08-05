package com.example.pokedexpokemon.dataLayer.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexpokemon.dataLayer.dao.BasePokemonDAO
import com.example.pokedexpokemon.dataLayer.entity.BasePokemonEntity

@Database(
    entities = [BasePokemonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class Database : RoomDatabase() {
    abstract fun getPokemonDAO(): BasePokemonDAO
}