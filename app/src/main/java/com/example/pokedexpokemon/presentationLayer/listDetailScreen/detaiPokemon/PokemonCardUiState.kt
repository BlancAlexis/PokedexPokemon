package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState

sealed class PokemonCardState {
    object Loading : PokemonCardState()
    object Error : PokemonCardState()
    data class onFirstSalveLoad( val uiStates: List<ListCardPokemon>): PokemonCardState()
}

data class ListCardPokemon(
    val image : String
)
/*
data class PokemonCardUiState(

)*/
