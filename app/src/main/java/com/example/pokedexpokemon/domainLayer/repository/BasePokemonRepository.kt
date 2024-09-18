package com.example.pokedexpokemon.domainLayer.repository

import BasePokemon
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.domainLayer.card_mapper.PokemonMapper.toDomain
import org.koin.core.component.KoinComponent

interface BasePokemonRepository {
    suspend fun getListBasePokemon(page : Int): List<BasePokemon>
    suspend fun getPokemon(index: String): BasePokemon
}

class BasePokemonRepositoryImpl(
    private val basePokemonDataSource: BasePokemonDataSource
) : BasePokemonRepository, KoinComponent {
    override suspend fun getListBasePokemon(page: Int): List<BasePokemon> {
        return basePokemonDataSource.getListBasePokemon(page).map { dto -> dto.toDomain() }
    }

    override suspend fun getPokemon(index: String): BasePokemon {
        return basePokemonDataSource.getPokemon(index).toDomain()

    }
}
