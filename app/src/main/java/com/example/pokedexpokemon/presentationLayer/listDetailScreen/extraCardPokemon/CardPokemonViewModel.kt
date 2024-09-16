package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedexpokemon.dataLayer.datasource.DeckPokemonRepository
import com.example.pokedexpokemon.dataLayer.room.Card
import com.example.pokedexpokemon.dataLayer.room.Deck
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.model.PokemonCard
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonCardByNameUseCase
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.CardPokemonUiState
import com.example.pokedexpokemon.presentationLayer.util.toPokemonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SaveCardUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(card: Card): Ressource<Unit> {
        return try {
            Ressource.Success(deckPokemonRepository.addCardToDeckPokemonUseCase(card))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}

class DeleteCardUseCase(
    private val deckPokemonRepository: DeckPokemonRepository
) : KoinComponent {
    suspend fun invoke(index: String): Ressource<Unit> {
        return try {
            Ressource.Success(deckPokemonRepository.removeCardFromDeckPokemonUseCase(index))
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}

class CreateDeckUseCase(
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

class CardPokemonViewModel(
    private val getPokemonCardByNameUseCase: GetPokemonCardByNameUseCase,
    private val saveCardUseCase: SaveCardUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val createDeckUseCase: CreateDeckUseCase
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


    fun onEvent(cardPokemonEvent: CardPokemonEvent) {
        when (cardPokemonEvent) {
            is CardPokemonEvent.saveCard -> {

            }
        }
    }

    fun PokemonCard.toUiState() = CardPokemonUiState(
        image = images,
        name = name,
        subtypes = subtypes.map { it.toPokemonType() },
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


sealed class CardPokemonEvent {
    data class saveCard(val card: PokemonCard) : CardPokemonEvent()
}

