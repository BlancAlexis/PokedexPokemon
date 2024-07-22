package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import Ability
import BasePokemon
import GameIndex
import Move
import Sprites
import Stat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.util.PokemonType
import com.example.pokedexpokemon.presentationLayer.util.toPokemonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ListDetailPokemonViewModel (
    private val getPokemon: GetPokemon,
    private val getPokemonList: GetPokemonList
) : ViewModel(){
    private val _uiState = MutableStateFlow(ListListDetails())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when(val result = getPokemonList.invoke()){
                is Ressource.Error -> { println("result.data ${result.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> {

                        val newData = result.data?.map { it.toUiState() } ?: emptyList()
                        _uiState.update {
                            it.copy(
                                list = newData
                            )
                        }
                     }
            }
        }
    }
}

private fun BasePokemon.toUiState(): ListDetailsPokemonUiState = ListDetailsPokemonUiState(
        name = this.name.replaceFirstChar { c -> c.uppercase() },
        weight = this.weight,
        height = this.height,
        baseExperience = this.baseExperience,
        type = this.type.map { it.toPokemonType() },
        abilities = this.abilities,
        gameIndices = this.gameIndices,
        nationalIndices = this.id,
        sprites = this.sprites,
        talent = this.abilities,
        roar = this.roar.urlLastestRoar
    )

data class ListListDetails(
    val list : List<ListDetailsPokemonUiState> = emptyList()
)

data class ListDetailsPokemonUiState(
    val name : String?= null,
    val weight : Int ?= null,
    val height: Int?= null,
    val type : List<PokemonType> = listOf(),
    val abilities: List<Ability>?= null,
    val baseExperience: Int?= null,
    val gameIndices: List<GameIndex>?= listOf(),
    val nationalIndices: Int? = null,
    val moves: List<Move> = listOf(),


    val sprites: Sprites?= null,
    val talent : List<Ability> ?= null,
    val roar: String ?= null,
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
