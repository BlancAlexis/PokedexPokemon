package com.example.pokedexpokemon.dataLayer

import Ability
import GameIndex
import Move
import Sprites
import Stat
import androidx.compose.foundation.lazy.LazyListState
import com.example.pokedexpokemon.presentationLayer.util.SealedPokemonType
import java.io.Serializable

data class ListDetailsUiState(
    val lazyColumnState: LazyListState = LazyListState(),
    val pokemonUiState: List<PokemonUiState> = listOf()
)

data class PokemonUiState(
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
) : Serializable