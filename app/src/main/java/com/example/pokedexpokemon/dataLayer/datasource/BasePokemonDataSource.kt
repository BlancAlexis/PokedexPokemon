package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.di.PokedexService
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import org.koin.core.component.KoinComponent

interface BasePokemonDataSource {
    suspend fun getListBasePokemon(): List<BasePokemonDTO>
    suspend fun getPokemon(index: String): BasePokemonDTO

}

class BasePokemonDataSourceImpl(
    private val pokedexService: PokedexService
) : BasePokemonDataSource, KoinComponent {
    override suspend fun getListBasePokemon(): List<BasePokemonDTO> {
        return pokedexService.fetchPokemonList().results.mapNotNull {
            extractPokemonId(it.url)?.let { id ->
                try {
                    getPokemon(id)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    override suspend fun getPokemon(index: String): BasePokemonDTO {
        return pokedexService.fetchPokemonInfo(index)
    }

    private fun extractPokemonId(url: String): String? {
        return url.trimEnd('/').split("/").lastOrNull { it.matches("\\d+".toRegex()) }
    }

}