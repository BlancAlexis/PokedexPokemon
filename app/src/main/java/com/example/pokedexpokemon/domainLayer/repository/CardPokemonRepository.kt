package com.example.pokedexpokemon.domainLayer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexpokemon.dataLayer.datasource.CardPagingSource
import com.example.pokedexpokemon.dataLayer.datasource.CardPokemonDataSource
import com.example.pokedexpokemon.dataLayer.datasource.toDomain
import com.example.pokedexpokemon.dataLayer.dto.PokemonCard
import kotlinx.coroutines.flow.Flow


interface CardPokemonRepository {
    suspend fun getPokemonByName(): Flow<PagingData<PokemonCard>>
}

class CardPokemonRepositoryImpl(
    private val cardPokemonDataSource: CardPagingSource
) : CardPokemonRepository {

    override suspend fun getPokemonByName(): Flow<PagingData<PokemonCard>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                cardPokemonDataSource
            }
        ).flow
    }
}