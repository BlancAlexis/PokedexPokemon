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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ListDetailPokemonViewModel(
    private val getPokemonList: GetPokemonList,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListDetailsState>(ListDetailsState.Loading)
    val uiState = _uiState.asStateFlow()

    val _isLoading = mutableStateOf(true)
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
            when (val result = getPokemonList.invoke()) {
                is Ressource.Error -> {
                    println("result.data ${result.error}")
                }

                is Ressource.Loading -> {}
                is Ressource.Success -> {
                    _isLoading.value = false
                    val newData = result.data?.map { it.toUiState() } ?: emptyList()
                    _uiState.update {
                        if (newData.isNotEmpty()) {
                            return@update ListDetailsState.onFirstSalveLoad(newData)
                        } else {
                            return@update it
                        }
                    }
                }
            }
        }
    }


    fun onEvent(event: ListDetailsPokemonEvent) {
        when (event) {
            is ListDetailsPokemonEvent.playRoar -> playPokemonRoar(event.roarUrl)
        }
    }
}

sealed interface ListDetailsPokemonEvent {
    data class playRoar(val roarUrl: String) : ListDetailsPokemonEvent
}

