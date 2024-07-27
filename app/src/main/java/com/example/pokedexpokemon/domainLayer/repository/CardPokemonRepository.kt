package com.example.pokedexpokemon.domainLayer.repository

import com.example.pokedexpokemon.dataLayer.datasource.CardPokemonDataSource
import com.example.pokedexpokemon.dataLayer.datasource.toDomain
import com.example.pokedexpokemon.dataLayer.dto.PokemonCard


interface CardPokemonRepository {
    suspend fun getPokemonByName(option: Map<String,String>): List<PokemonCard>
}

class CardPokemonRepositoryImpl(
    private val cardPokemonDataSource: CardPokemonDataSource
) : CardPokemonRepository {

    override suspend fun getPokemonByName(option: Map<String,String>): List<PokemonCard> {
        return cardPokemonDataSource.getPokemonByName(option).map {
            it.toDomain()
        }
    }
}