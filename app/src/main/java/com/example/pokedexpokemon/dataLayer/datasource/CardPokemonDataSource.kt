package com.example.pokedexpokemon.dataLayer.datasource

import android.util.Log
import com.example.pokedexpokemon.dataLayer.di.PokemonCardService
import com.example.pokedexpokemon.dataLayer.dto.ResponseCardApi

interface CardPokemonDataSource {
    suspend fun getPokemonByName(pageNumber: Int, name: String): ResponseCardApi
}

class CardPokemonDataSourceImpl(
    private val pokemonCardService: PokemonCardService
) : CardPokemonDataSource {

    override suspend fun getPokemonByName(pageNumber: Int, name: String): ResponseCardApi {
        Log.d("CardPokemonDataSourceImpl", "getPokemonByNameDataSource")
        return pokemonCardService.getPokemonCardByName(pageNumber, name = name)
    }
}

