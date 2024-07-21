package com.example.pokedexpokemon.domainLayer.repository

import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent

interface BasePokemonRepository {
    suspend fun getListBasePokemon(): List<BasePokemonDTO>
    suspend fun getPokemon(index : String) : BasePokemonDTO
}
class BasePokemonRepositoryImpl(
    private val basePokemonDataSource: BasePokemonDataSource
) : BasePokemonRepository, KoinComponent {
    override suspend fun getListBasePokemon(): List<BasePokemonDTO> {
         return basePokemonDataSource.getListBasePokemon()
    }

    override suspend fun getPokemon(index : String): BasePokemonDTO {
        return basePokemonDataSource.getPokemon(index)

    }
}
