package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.di.PokedexService
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent

interface BasePokemonDataSource {
    suspend fun getListBasePokemon(): List<BasePokemonDTO>
    suspend fun getPokemon() : BasePokemonDTO

}

class BasePokemonDataSourceImpl(
    private val pokedexService: PokedexService
) : BasePokemonDataSource, KoinComponent {
    override suspend fun getListBasePokemon(): List<BasePokemonDTO> {
        println("${pokedexService.fetchPokemonList()}")
         pokedexService.fetchPokemonList().results
        return listOf()
    }

    override suspend fun getPokemon(): BasePokemonDTO {
        return pokedexService.fetchPokemonInfo("1")
    }
}