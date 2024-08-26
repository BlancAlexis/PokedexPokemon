package com.example.pokedexpokemon.dataLayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardEntity")
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val cardId: Long = 0,
    val name: String,
    val image: String,

    )