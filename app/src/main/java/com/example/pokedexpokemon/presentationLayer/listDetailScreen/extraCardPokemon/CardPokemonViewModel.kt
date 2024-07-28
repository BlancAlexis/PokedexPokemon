package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedexpokemon.domainLayer.model.PokemonCard
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonCardByNameUseCase
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.CardPokemonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CardPokemonViewModel(
    private val getPokemonCardByNameUseCase: GetPokemonCardByNameUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<PagingData<CardPokemonUiState>> =
        MutableStateFlow(value = PagingData.empty())
    val uiState = _uiState.asStateFlow()


    private suspend fun getCard() {
        getPokemonCardByNameUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _uiState.value = it.map { it.toUiState() }
            }
    }

    init {
        viewModelScope.launch {
            getCard()
        }
    }

    fun PokemonCard.toUiState() = CardPokemonUiState(
        image = images
    )

}

