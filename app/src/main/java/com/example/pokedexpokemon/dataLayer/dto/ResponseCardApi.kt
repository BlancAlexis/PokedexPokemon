package com.example.pokedexpokemon.dataLayer.dto

import com.google.gson.annotations.SerializedName


data class ResponseCardApi(
    val data: List<PokemonCardDTO>,
    val page: Int,
    val pageSize: Int,
    val count: Int,
    val totalCount: Int
)

data class PokemonCardDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("supertype") val supertype: String,
    @SerializedName("subtypes") val subtypes: List<String>,
    @SerializedName("hp") val hp: String,
    @SerializedName("types") val types: List<String>,
    @SerializedName("set") val setDTO: SetDTO,
    @SerializedName("number") val number: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("rarity") val rarity: String?,
    @SerializedName("legalities") val legalitiesDTO: LegalitiesDTO,
    @SerializedName("images") val imagesDTO: ImagesDTO,
    @SerializedName("cardmarket") val tcgplayer: CardmarketDTO
)


data class SetDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("series") val series: String,
    @SerializedName("printedTotal") val printedTotal: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("legalities") val legalitiesDTO: LegalitiesDTO,
    @SerializedName("ptcgoCode") val ptcgoCode: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("updatedAt") val updatedAt: String,
)

data class LegalitiesDTO(
    @SerializedName("unlimited") val unlimited: String,
)

data class ImagesDTO(
    @SerializedName("small") val small: String,
    @SerializedName("large") val large: String
)

data class CardmarketDTO(
    @SerializedName("prices") val holofoilDTO: HolofoilDTO,
    @SerializedName("updatedAt") val updatedAt: String
)

data class HolofoilDTO(
    @SerializedName("lowPrice") val low: Double,
    @SerializedName("averageSellPrice") val mid: Double,
)



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
    val logoSeries : String,
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