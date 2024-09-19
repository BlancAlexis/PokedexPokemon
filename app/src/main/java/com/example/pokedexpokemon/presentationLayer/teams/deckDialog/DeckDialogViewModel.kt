package com.example.pokedexpokemon.presentationLayer.teams.deckDialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetDeckUseCase
import com.example.pokedexpokemon.domainLayer.usecase.SaveDeckUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class DeckDialogViewModel(
    private val getDeckUseCase: GetDeckUseCase,
    private val saveDeckUseCase: SaveDeckUseCase
) : ViewModel() {
    private val uInt: MutableStateFlow<DeckDialogUiState> = MutableStateFlow(DeckDialogUiState())
    val uistate = uInt.asStateFlow()

    init {
        viewModelScope.launch {
            getDeckUseCase.invoke().collect {
                when (val res = it) {
                    is Ressource.Error -> TODO()
                    is Ressource.Loading -> TODO()
                    is Ressource.Success -> {
                        uInt.update {
                            it.copy(
                                deckAvailable = res.data?.map { deck -> DeckStatusUiState(deck.name) }
                                    ?: emptyList())

                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: DeckDialogEvent) {
        when (event) {
            is DeckDialogEvent.SaveDeck -> {
                viewModelScope.launch {
                    // saveDeckUseCase.invoke(event.deck)
                }
            }

            is DeckDialogEvent.TriggerSelectedDeck -> {
                uInt.update { currentState ->
                    val updatedDecks = currentState.deckAvailable?.mapIndexed { index, deck ->
                        if (index == event.index) {
                            deck.copy(isSelect = !deck.isSelect)
                        } else {
                            deck
                        }
                    }
                    currentState.copy(deckAvailable = updatedDecks)
                }
            }
        }
    }
}

sealed class DeckDialogEvent {
    object SaveDeck : DeckDialogEvent()
    data class TriggerSelectedDeck(val index: Int) : DeckDialogEvent()
}

data class DeckDialogUiState @OptIn(ExperimentalFoundationApi::class) constructor(
    val deckAvailable: List<DeckStatusUiState>? = null,
    val newDeck: TextFieldState = TextFieldState()
)

data class DeckStatusUiState(
    val name: String,
    val isSelect: Boolean = false
)