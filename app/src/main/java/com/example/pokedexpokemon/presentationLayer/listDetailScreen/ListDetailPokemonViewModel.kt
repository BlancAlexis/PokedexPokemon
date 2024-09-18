package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.BasePokemonMapper.toUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class ListDetailPokemonViewModel(
    private val getPokemonList: GetPokemonList,
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<ListDetailsPokemonUiState>>(listOf())
    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    internal val screenUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    val uiState = _uiState
        .onStart {
            viewModelScope.launch {
                loadNextPagePokemon()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _uiState.value
        )

    private fun playPokemonSound(soundUrl: String) {
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(soundUrl)
            prepare()
            start()
        }
    }

    private suspend fun loadNextPagePokemon (){
            pokemonFetchingIndex.flatMapLatest { page ->
                flow {
                    emit(HomeUiState.Loading)
                    when (val result = getPokemonList.invoke(page)) {
                        is Ressource.Error -> emit(HomeUiState.Error(result.message))
                        is Ressource.Loading -> emit(HomeUiState.Loading)
                        is Ressource.Success -> {
                            val newData = result.data?.map { it.toUiState() } ?: emptyList()
                            if (newData.isNotEmpty()) {
                                emit(HomeUiState.Idle)
                                _uiState.update {
                                    it + newData
                                }
                            }
                        }
                    }
                }
            }.collect {
                screenUiState.value = it
            }
        }


    fun onEvent(event: ListDetailsPokemonEvent) {
        when (event) {
            is ListDetailsPokemonEvent.playRoar -> playPokemonSound(event.roarUrl)
            ListDetailsPokemonEvent.loadMore -> {
                if (screenUiState.value != HomeUiState.Loading) {
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

@Stable
internal sealed interface HomeUiState {

    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Error(val message: String?) : HomeUiState
}