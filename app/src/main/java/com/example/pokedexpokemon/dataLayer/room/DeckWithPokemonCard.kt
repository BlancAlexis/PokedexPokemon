package com.example.pokedexpokemon.dataLayer.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pokedexpokemon.dataLayer.entity.CardEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckCardCrossRefEntity
import com.example.pokedexpokemon.dataLayer.entity.DeckEntity

data class DeckWithPokemonCard(
    @Embedded val deck: DeckEntity,
    @Relation(
        parentColumn = "deckId",
        entityColumn = "cardId",
        associateBy = Junction(DeckCardCrossRefEntity::class)
    )
    val cards: List<CardEntity>
)