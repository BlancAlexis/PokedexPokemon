package com.example.pokedexpokemon.domainLayer.card_mapper


import com.example.pokedexpokemon.dataLayer.dto.PokemonCardDTO
import com.example.pokedexpokemon.domainLayer.model.CardPrice
import com.example.pokedexpokemon.domainLayer.model.PokemonCard
import com.example.pokedexpokemon.domainLayer.model.Prices

object CardMapper {

    fun PokemonCardDTO.toDomain() = PokemonCard(
        artist = artist,
        name = name,
        hp = hp,
        //  set = setDTO.,
        types = types,
        images = imagesDTO.small,
        number = number,
        rarity = rarity ?: "",
        subtypes = subtypes,
        cardPrice = CardPrice(
            "",
            Prices(23.0, 34.0, 45.0, 45.0, 23.0)
        ),
        supertype = supertype,
    )

}