package com.example.pokedexpokemon.presentationLayer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.datasource.DeckPokemonRepository
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class GetDeckUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(): Flow<Ressource<List<Deck>>> {
        return try {
            deckPokemonRepository.getAllDeckPokemonUseCase().map {
                Ressource.Success(it)
            }
        } catch (e: Exception) {
            flowOf(Ressource.Error(exception = e))
        }
    }

}

class SaveDeckUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(deck: Deck): Ressource<Unit> {
        return try {
            Ressource.Success(deckPokemonRepository.insertDeckPokemonUseCase(deck = deck))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}
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
    data class TriggerSelectedDeck(val index : Int) : DeckDialogEvent()
}

data class DeckDialogUiState @OptIn(ExperimentalFoundationApi::class) constructor(
    val deckAvailable: List<DeckStatusUiState>? = null,
    val newDeck: TextFieldState = TextFieldState()
)

data class DeckStatusUiState(
    val name: String,
    val isSelect: Boolean = false
)