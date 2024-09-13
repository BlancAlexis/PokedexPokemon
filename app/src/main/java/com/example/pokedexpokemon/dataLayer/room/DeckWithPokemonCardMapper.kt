package com.example.pokedexpokemon.dataLayer.room

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokedexpokemon.dataLayer.entity.CardEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckEntity

data class DeckWithPokemonCardMapper(
    @Embedded val deck: DeckEntity,
    @Relation(
        parentColumn = "deckId",
        entityColumn = "deckId"
    )
    val cards: List<CardEntity>
)

data class Deck(
    val id: Int?= null,
    val name: String,
    val cards: List<Card>
)

data class Card(
    val id: Int?= null,
    val name: String,
    val rarity: String,
)