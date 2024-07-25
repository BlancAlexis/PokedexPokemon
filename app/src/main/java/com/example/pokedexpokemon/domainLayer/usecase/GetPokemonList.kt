package com.example.pokedexpokemon.domainLayer.usecase

import BasePokemon
import androidx.paging.PagingData
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

class GetPokemonList(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {

    suspend fun invoke(): Flow<PagingData<BasePokemon>> {
        return basePokemonRepository.getListBasePokemon()
    }

}