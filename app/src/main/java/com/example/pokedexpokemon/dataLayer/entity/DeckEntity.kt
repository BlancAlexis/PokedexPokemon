package com.example.pokedexpokemon.dataLayer.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "deckEntity")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val deckId: Long = 0,
    val name: String,
)



