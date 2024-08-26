package com.example.pokedexpokemon.dataLayer.entity

import androidx.room.Entity

@Entity(primaryKeys = ["deckId", "cardId"])
data class DeckCardCrossRefEntity(
    val deckId: Long,
    val cardId: Long
)
