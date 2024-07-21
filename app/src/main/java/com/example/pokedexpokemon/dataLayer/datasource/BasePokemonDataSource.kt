package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.di.PokedexService
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent

interface BasePokemonDataSource {
    suspend fun getListBasePokemon(): Ressource<List<BasePokemonDTO>>
}

class BasePokemonDataSourceImpl(
    private val pokedexService: PokedexService
) : BasePokemonDataSource, KoinComponent {
    override suspend fun getListBasePokemon(): Ressource<List<BasePokemonDTO>> {
        return pokedexService.fetchPokemonList()
    }
}