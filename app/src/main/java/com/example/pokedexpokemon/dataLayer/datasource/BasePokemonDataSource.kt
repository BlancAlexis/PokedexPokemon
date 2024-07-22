package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.di.PokedexService
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent

interface BasePokemonDataSource {
    suspend fun getListBasePokemon(): List<BasePokemonDTO>
    suspend fun getPokemon(index : String) : BasePokemonDTO

}

class BasePokemonDataSourceImpl(
    private val pokedexService: PokedexService
) : BasePokemonDataSource, KoinComponent {
    override suspend fun getListBasePokemon(): List<BasePokemonDTO> {
        return pokedexService.fetchPokemonList().results.map { extractPokemonId(it.url)?.let { getPokemon(it) }!! }
    }

    override suspend fun getPokemon(index : String): BasePokemonDTO {
        return pokedexService.fetchPokemonInfo(index)
    }

    private fun extractPokemonId(url: String): String? {
        val urlParts = url.split("/")
        for (part in urlParts.reversed()) {
            if (part.matches("\\d+".toRegex())) {
                return part
            }
        }
        return null // TODO gérer l'erreur
    }
}