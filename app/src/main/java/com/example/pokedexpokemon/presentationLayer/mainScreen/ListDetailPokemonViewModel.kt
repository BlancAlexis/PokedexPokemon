package com.example.pokedexpokemon.presentationLayer.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListDetailPokemonViewModel (
    private val getPokemon: GetPokemon,
    private val getPokemonList: GetPokemonList
) : ViewModel(){
    private val _uiState = MutableStateFlow(ListDetailsPokemonUiState())
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
    val sprite : String ?= null,
    val name : String?= null,
    val type : List<String> = listOf(),

    val talent : String ?= null,
    val weight : String ?= null,
)