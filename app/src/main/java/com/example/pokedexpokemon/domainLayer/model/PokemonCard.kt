package com.example.pokedexpokemon.domainLayer.model

data class PokemonCard(
    val name: String,
    val supertype: String,
    val subtypes: List<String>,
    val hp: String,
    val types: List<String>,
    val number: String,
    val artist: String,
    val rarity: String,
    val images: String,
    val cardPrice: CardPrice
) {

}

data class Legalities(
    val unlimited: String,
    val expanded: String
)

data class Images(
    val logoSeries: String,
    val cardLarge: String,
)

data class CardPrice(
    val updatedAt: String,
    val prices: Prices
)

data class Prices(
    val low: Double,
    val mid: Double,
    val high: Double,
    val market: Double,
    val directLow: Double
)