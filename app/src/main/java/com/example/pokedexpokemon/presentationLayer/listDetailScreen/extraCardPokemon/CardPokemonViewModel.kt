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


    private suspend fun getCard(name: String) {
        getPokemonCardByNameUseCase.invoke(name)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _uiState.value = it.map { it.toUiState() }
            }
    }

    init {

    }

    fun PokemonCard.toUiState() = CardPokemonUiState(
        image = images,
        name = name,
        subtypes = subtypes,
        types = types,
        number = number,
        artist = artist,
        rarity = rarity,
        cardPrice = cardPrice
    )

    fun getPokemonByName(name: String) {
        viewModelScope.launch {
            getCard(name)
        }
    }

}

