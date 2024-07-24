package com.example.pokedexpokemon.dataLayer.datasource

import com.example.pokedexpokemon.dataLayer.di.PokemonCardService
import com.example.pokedexpokemon.dataLayer.dto.CardPrice
import com.example.pokedexpokemon.dataLayer.dto.PokemonCard
import com.example.pokedexpokemon.dataLayer.dto.PokemonCardDTO
import com.example.pokedexpokemon.dataLayer.dto.Prices

interface CardPokemonDataSource {
    suspend fun getPokemonByName(option: Map<String,String>): List<PokemonCardDTO>
}

class CardPokemonDataSourceImpl(
    private val pokemonCardService: PokemonCardService
) : CardPokemonDataSource {

    override suspend fun getPokemonByName(option: Map<String,String>): List<PokemonCardDTO> {
        println(" datasource ${pokemonCardService.getPokemonCardByName(option).data}")
        return pokemonCardService.getPokemonCardByName(option).data
    }
}


fun PokemonCardDTO.toDomain() = PokemonCard(
    artist = artist,
    name = name,
    hp = hp,
  //  set = setDTO.,
    types = types,
    images = imagesDTO.large,
    number = number,
    rarity = rarity,
    subtypes = subtypes,
    cardPrice = CardPrice(
        "",
        Prices(23.0,34.0,45.0,45.0,23.0)
    ),
    supertype = supertype,
)

