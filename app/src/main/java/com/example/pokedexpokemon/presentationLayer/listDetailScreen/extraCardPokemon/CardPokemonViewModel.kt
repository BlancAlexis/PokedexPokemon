package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.ListDetailsState
import com.example.pokedexpokemon.dataLayer.dto.PokemonCard
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonCardByNameUseCase
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.ListCardPokemon
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.PokemonCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardPokemonViewModel (
    private val getPokemonCardByNameUseCase: GetPokemonCardByNameUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<PokemonCardState>(PokemonCardState.Loading)
    val uiState = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            val a = mapOf("name" to "Charizard")
                when(val result = getPokemonCardByNameUseCase.invoke(a)){
                    is Ressource.Error -> {}
                    is Ressource.Loading -> {}
                    is Ressource.Success -> {
val newData = result.data?.map { it.toUiState() } ?: emptyList()
                        _uiState.update {
                            // Check if data is valid or perform other operations (be careful here)
                            if (newData.isNotEmpty()) {
                                return@update PokemonCardState.onFirstSalveLoad(newData)
                            } else {
                                // Handle empty data case (optional)
                                return@update it
                            }
                    }
                }
        }

    }    }
        fun PokemonCard.toUiState() = ListCardPokemon(
                image = images
            )

}

