package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import BasePokemon
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.util.toPokemonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class ListDetailPokemonViewModel(
    private val getPokemonList: GetPokemonList,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PagingData<ListDetailsPokemonUiState>> =
        MutableStateFlow(value = PagingData.empty())
    val uiState = _uiState.asStateFlow()


    fun playPokemonRoar(roarUrl: String) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA).build()
            )
            setDataSource(roarUrl)
            prepare()
            start()
        }
    }

    private suspend fun getPokemon() {
        getPokemonList.invoke().distinctUntilChanged().cachedIn(viewModelScope).collect {
            _uiState.value = it.map { it.toUiState() }
        }
    }

    init {
        viewModelScope.launch {
            getPokemon()
        }
    }

    private fun BasePokemon.toUiState(): ListDetailsPokemonUiState =
        ListDetailsPokemonUiState(name = this.name.replaceFirstChar { char -> char.uppercase() },
            weight = this.weight,
            height = this.height,
            baseExperience = this.baseExperience,
            type = this.type.map { it.name.toPokemonType() }.toList(),
            abilities = this.abilities,
            gameIndices = this.gameIndices,
            nationalIndices = this.id,
            sprites = this.sprites,
            talent = this.abilities,
            roar = this.roar.urlLastestRoar,
            moves = this.moves.filter { it.levelLearnedAt != 0 }.sortedBy { it.levelLearnedAt })

    fun onEvent(event: ListDetailsPokemonEvent) {
        when (event) {
            is ListDetailsPokemonEvent.playRoar -> playPokemonRoar(event.roarUrl)
        }
    }
}

sealed interface ListDetailsPokemonEvent {
    data class playRoar(val roarUrl: String) : ListDetailsPokemonEvent
}

