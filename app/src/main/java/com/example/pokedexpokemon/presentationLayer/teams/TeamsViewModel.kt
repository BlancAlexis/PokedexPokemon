package com.example.pokedexpokemon.presentationLayer.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.datasource.DeleteDeckPokemonUseCase
import com.example.pokedexpokemon.dataLayer.datasource.GetAllDeckPokemonUseCase
import com.example.pokedexpokemon.dataLayer.datasource.InsertDeckPokemonUseCase
import com.example.pokedexpokemon.dataLayer.room.Card
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamViewModel(
    private val getAllDeckPokemonUseCase: GetAllDeckPokemonUseCase,
    private val insertDeckPokemonUseCase: InsertDeckPokemonUseCase,
    private val deleteDeckPokemonUseCase: DeleteDeckPokemonUseCase,
    /* private val addCardToDeckPokemonUseCase: AddCardToDeckPokemonUseCase,
     private val removeCardFromDeckPokemonUseCase: RemoveCardFromDeckPokemonUseCase*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<DeckUiState>>(listOf())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            insertDeckPokemonUseCase.invoke(
                Deck(
                    name = "test2", cards = listOf(Card(1, "test", "test"))
                )
            )

            getAllDeckPokemonUseCase.invoke().collect { it ->
                when (it) {
                    is Ressource.Error -> {}
                    is Ressource.Loading -> {}
                    is Ressource.Success -> _uiState.update { _ ->
                        it.data?.map {
                            it.toUiState()
                        } ?: listOf()
                    }
                }
            }
        }
    }


    private fun deleteDeck(deck: DeckUiState) {
        viewModelScope.launch {
            deleteDeckPokemonUseCase.invoke(deck.name)
        }
    }

    fun onEvent(event: TeamEvent) {
        when (event) {
            is TeamEvent.DeleteDeck -> deleteDeck(event.deck)
        }
    }
}

private fun Deck.toUiState(): DeckUiState {
    return DeckUiState(id = id ?: 0, name = name, cards = cards.map { it.toUiState() })
}

private fun Card.toUiState(): PokemonCardUiState {
    return PokemonCardUiState(
        id = id ?: 0, name = name, number = ""
    )
}

data class DeckUiState(
    val id: Int, val name: String, val cards: List<PokemonCardUiState>
)

data class PokemonCardUiState(
    val id: Int, val name: String, val number: String
)

sealed class TeamEvent {
    data class DeleteDeck(val deck: DeckUiState) : TeamEvent()
}