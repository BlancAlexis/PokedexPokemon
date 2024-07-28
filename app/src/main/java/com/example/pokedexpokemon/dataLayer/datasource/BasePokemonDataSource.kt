package com.example.pokedexpokemon.dataLayer.datasource

import android.util.Log
import com.example.pokedexpokemon.dataLayer.di.PokedexService
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.PokedexResponse
import org.koin.core.component.KoinComponent

interface BasePokemonDataSource {
    suspend fun getListBasePokemon(offset : Int): PokedexResponse
    suspend fun getPokemon(index: String): BasePokemonDTO
    fun extractPokemonId(url : String) : String?
}

class BasePokemonDataSourceImpl(
    private val pokedexService: PokedexService
) : BasePokemonDataSource {
    override suspend fun getListBasePokemon(offset : Int): PokedexResponse {
        Log.d("BasePokemonDataSourceImpl", "getListBasePokemon called with offset: $offset")
        return pokedexService.fetchPokemonList(offset)
    }

    override suspend fun getPokemon(index: String): BasePokemonDTO {
        return pokedexService.fetchPokemonInfo(index)
    }

    override fun extractPokemonId(url: String): String? {
        return url.trimEnd('/').split("/").lastOrNull { it.matches("\\d+".toRegex()) }
    }

}