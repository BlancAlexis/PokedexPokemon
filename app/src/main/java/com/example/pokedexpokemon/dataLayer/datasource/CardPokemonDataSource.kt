package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.di.PokemonCardService
import com.example.pokedexpokemon.dataLayer.dto.ResponseCardApi

interface CardPokemonDataSource {
    suspend fun getPokemonByName(pageNumber: Int, name: String): ResponseCardApi
}

class CardPokemonDataSourceImpl(
    private val pokemonCardService: PokemonCardService
) : CardPokemonDataSource {

    override suspend fun getPokemonByName(pageNumber: Int, name: String): ResponseCardApi {
        return pokemonCardService.getPokemonCardByName(pageNumber, name = name)
    }
}

