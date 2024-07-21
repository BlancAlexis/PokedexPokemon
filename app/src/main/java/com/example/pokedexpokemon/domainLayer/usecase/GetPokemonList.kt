package com.example.pokedexpokemon.domainLayer.usecase

import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.model.BasePokemon
import com.example.pokedexpokemon.domainLayer.repository.BasePokemonRepository
import org.koin.core.component.KoinComponent

class GetPokemonList(
    private val basePokemonRepository: BasePokemonRepository
) : KoinComponent {
    suspend fun invoke(): Ressource<List<BasePokemon>> {
        return basePokemonRepository.getListBasePokemon()
    }

}