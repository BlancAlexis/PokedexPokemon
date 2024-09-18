package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.ListDetailsState
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.BasePokemonMapper.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class ListDetailPokemonViewModel(
    private val getPokemonList: GetPokemonList,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListDetailsState>(ListDetailsState.Loading)
    val uiState = _uiState.asStateFlow()
    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    fun playPokemonRoar(roarUrl: String) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(roarUrl)
            prepare()
            start()
        }
    }

    init {
        viewModelScope.launch {
            pokemonFetchingIndex.flatMapLatest {page ->
                flow {
                    val result = getPokemonList.invoke(page)
                    when (result) {
                        is Ressource.Error -> emit(ListDetailsState.Error)
                        is Ressource.Loading -> {}
                        is Ressource.Success -> {
                            val newData = result.data?.map { it.toUiState() } ?: emptyList()
                            if (newData.isNotEmpty()) {
                                _uiState.update { currentState ->
                                    when (currentState) {
                                        is ListDetailsState.onFirstSalveLoad ->{
                                            ListDetailsState.onFirstSalveLoad(currentState.uiStates + newData)
                                        }
                                        else -> ListDetailsState.onFirstSalveLoad(newData)
                                    }
                                }
                            }
                        }
                    }
                }
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun onEvent(event: ListDetailsPokemonEvent) {
        when (event) {
            is ListDetailsPokemonEvent.playRoar -> playPokemonRoar(event.roarUrl)
            ListDetailsPokemonEvent.loadMore ->{
                if (uiState.value != ListDetailsState.Loading) {
                    pokemonFetchingIndex.value++
                }
            }
        }
    }
}

sealed interface ListDetailsPokemonEvent {
    data class playRoar(val roarUrl: String) : ListDetailsPokemonEvent
    object loadMore : ListDetailsPokemonEvent
}

