package com.example.pokedexpokemon.domainLayer.usecase

import androidx.paging.PagingData
import com.example.pokedexpokemon.domainLayer.model.PokemonCard
import com.example.pokedexpokemon.domainLayer.repository.CardPokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonCardByNameUseCase(private val pokemonRepository: CardPokemonRepository) {

    suspend operator fun invoke(name : String): Flow<PagingData<PokemonCard>> {
        return pokemonRepository.getPokemonByName(name)
    }

}