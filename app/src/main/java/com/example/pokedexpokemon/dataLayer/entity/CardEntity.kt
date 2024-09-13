package com.example.pokedexpokemon.dataLayer.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cardEntity",
    foreignKeys = [
        ForeignKey(
            entity = DeckEntity::class,
            parentColumns = ["deckId"],
            childColumns = ["deckId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val cardId: Long = 0,
    val name: String,
    val image: String,
    val deckId: Long
)