package com.example.pokedexpokemon.dataLayer

import Ability
import GameIndex
import Move
import Sprites
import Stat
import com.example.pokedexpokemon.presentationLayer.util.SealedPokemonType

sealed class ListDetailsState {
    object Loading : ListDetailsState()
    object Error : ListDetailsState()
    data class onFirstSalveLoad(val uiStates: List<ListDetailsPokemonUiState>) : ListDetailsState()
}

data class ListDetailsPokemonUiState(
    val name: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val type: List<SealedPokemonType> = listOf(),
    val abilities: List<Ability>? = null,
    val baseExperience: Int? = null,
    val gameIndices: List<GameIndex>? = listOf(),
    val nationalIndices: Int,
    val moves: List<Move> = listOf(),


    val sprites: Sprites? = null,
    val talent: List<Ability>? = null,
    val roar: String? = null,
    val stats: List<Stat> = listOf()
)