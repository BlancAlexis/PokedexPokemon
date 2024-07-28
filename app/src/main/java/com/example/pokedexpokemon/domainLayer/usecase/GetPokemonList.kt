package com.example.pokedexpokemon.domainLayer.usecase

import BasePokemon
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import org.koin.core.component.KoinComponent

class GetPokemonList(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {
    suspend fun invoke(): Ressource<List<BasePokemon>> {
        return try {
            Ressource.Success(basePokemonRepository.getListBasePokemon())
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

}