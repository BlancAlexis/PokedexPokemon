package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import Ability
import GameIndex
import Move
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.dto.Sprites
import com.example.pokedexpokemon.dataLayer.dto.Stat
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListDetailPokemonViewModel (
    private val getPokemon: GetPokemon,
    private val getPokemonList: GetPokemonList
) : ViewModel(){
    private val _uiState = MutableStateFlow<List<ListDetailsPokemonUiState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when(val resullt = getPokemon.invoke("1")){
                is Ressource.Error -> { println("result.data suicde ${resullt.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> println("result.data suicde ${resullt.data}")
            }
        }
        viewModelScope.launch {
            when(val resullt = getPokemonList.invoke()){
                is Ressource.Error -> { println("result.data ${resullt.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> println("result.data ${resullt.data}")
            }
        }
    }
}

data class ListDetailsPokemonUiState(
    val sprites: Sprites?= null,
    val name : String?= null,
    val type : List<String> = listOf(),

    val talent : String ?= null,
    val weight : String ?= null,
    val abilities: List<Ability>?= null,
    val baseExperience: Int?= null,
    val roar: String ?= null,
    val gameIndices: List<GameIndex>?= listOf(),
    val height: Int?= null,
    val id: Int,
    val moves: List<Move> = listOf(),
    val stats: List<Stat> = listOf()
)

/*
data class ListScreenUiState(
    val id: Int? = null,
    val sprites: Sprites? = null,
    val name: String? = null,
    val type: List<String> = emptyList()
)

data class DetailScreenUiState(
    val basicField : BasicPokemonField?= null,

    val talent: String? = null,
    val weight: String? = null,
    val abilities: List<Ability>? = null,
    val baseExperience: Int? = null,
    val roar: String? = null,
    val gameIndices: List<GameIndex>? = emptyList(),
    val height: Int? = null,
    val moves: List<Move> = emptyList(),
    val stats: List<Stat> = emptyList()
)
typealias BasicPokemonField = ListScreenUiState*/
