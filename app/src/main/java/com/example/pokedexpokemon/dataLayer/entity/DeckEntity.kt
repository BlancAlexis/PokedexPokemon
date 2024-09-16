package com.example.pokedexpokemon.dataLayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deckEntity")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val deckId: Long = 0,
    val name: String,
)



