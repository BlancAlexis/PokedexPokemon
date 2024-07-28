package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

sealed class PokemonCardState {
    object Loading : PokemonCardState()
    object Error : PokemonCardState()
    data class onFirstSalveLoad( val uiStates: PokemonCardList): PokemonCardState()
}

data class PokemonCardList(
    val list : List<CardPokemonUiState>
)
data class CardPokemonUiState(
    val image : String
)
/*
data class PokemonCardUiState(

)*/
