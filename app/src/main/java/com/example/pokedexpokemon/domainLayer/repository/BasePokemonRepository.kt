package com.example.pokedexpokemon.domainLayer.repository

import BasePokemon
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexpokemon.dataLayer.datasource.BasePokemonDataSource
import com.example.pokedexpokemon.dataLayer.paging.PokemonPagingSource
import com.example.pokedexpokemon.domainLayer.card_mapper.PokemonMapper.toDomain
import kotlinx.coroutines.flow.Flow

interface BasePokemonRepository {
    suspend fun getListBasePokemon(): Flow<PagingData<BasePokemon>>
    suspend fun getPokemon(index: String): BasePokemon
}

class BasePokemonRepositoryImpl(
    private val pokemonPagingSource: PokemonPagingSource,
    private val basePokemonDataSource: BasePokemonDataSource
) : BasePokemonRepository {
    override suspend fun getListBasePokemon(): Flow<PagingData<BasePokemon>> {
        println("base repo")
        return Pager(
            config = PagingConfig(pageSize = 30, prefetchDistance = 2),
            pagingSourceFactory = {
                pokemonPagingSource
            }
        ).flow
    }

    override suspend fun getPokemon(index: String): BasePokemon {
        return basePokemonDataSource.getPokemon(index).toDomain()

    }
}

