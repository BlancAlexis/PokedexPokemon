package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import Ability
import BasePokemon
import GameIndex
import Move
import Sprites
import Stat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.dataLayer.ListDetailsUiState
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
    private val getPokemonList: GetPokemonList
) : ViewModel(){
    private val _uiState = MutableStateFlow(ListDetailsUiState())
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
    name = this.name.replaceFirstChar { char -> char.uppercase() },
    weight = this.weight,
    height = this.height,
    baseExperience = this.baseExperience,
    type = this.type.map { it.name.toPokemonType() }.toList(),
    abilities = this.abilities,
    gameIndices = this.gameIndices,
    nationalIndices = this.id,
    sprites = this.sprites,
    talent = this.abilities,
    roar = this.roar.urlLastestRoar
)


