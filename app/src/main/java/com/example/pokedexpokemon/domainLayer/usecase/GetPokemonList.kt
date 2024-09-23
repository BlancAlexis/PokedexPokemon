package com.example.pokedexpokemon.domainLayer.usecase

import BasePokemon
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import org.koin.core.component.KoinComponent

class GetPokemonList(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {
    suspend fun invoke(page: Int): Ressource<List<BasePokemon>> {
        return try {
            Ressource.Success(basePokemonRepository.getListBasePokemon(page))
        } catch (e: Exception) {

            Ressource.Error(e)
        }

    }
}