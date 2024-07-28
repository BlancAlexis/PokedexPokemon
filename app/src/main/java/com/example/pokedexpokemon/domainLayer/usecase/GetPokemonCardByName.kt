package com.example.pokedexpokemon.domainLayer.usecase

import androidx.paging.PagingData
import com.example.pokedexpokemon.dataLayer.dto.PokemonCard
import com.example.pokedexpokemon.domainLayer.repository.CardPokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonCardByNameUseCase(private val pokemonRepository: CardPokemonRepository) {

    suspend operator fun invoke(): Flow<PagingData<PokemonCard>> {
        return pokemonRepository.getPokemonByName()
    }

}