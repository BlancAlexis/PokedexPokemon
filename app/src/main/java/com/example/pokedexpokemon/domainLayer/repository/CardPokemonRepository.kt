package com.example.pokedexpokemon.domainLayer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexpokemon.dataLayer.paging.CardPagingSource
import com.example.pokedexpokemon.domainLayer.model.PokemonCard
import kotlinx.coroutines.flow.Flow


interface CardPokemonRepository {
    suspend fun getPokemonByName(name : String): Flow<PagingData<PokemonCard>>
}

class CardPokemonRepositoryImpl(
    private val cardPokemonPagingSource: CardPagingSource
) : CardPokemonRepository {

    override suspend fun getPokemonByName(name : String): Flow<PagingData<PokemonCard>> {
        cardPokemonPagingSource.name = "name:$name"
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2), // truc bizarre
            pagingSourceFactory = {
                cardPokemonPagingSource
            }
        ).flow
    }
}