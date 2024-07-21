package com.example.pokedexpokemon.dataLayer.dto

import com.google.gson.annotations.SerializedName


data class PokedexResponse(
    @SerializedName(value = "count") val count: Int,
    @SerializedName(value = "next") val next: String?,
    @SerializedName(value = "previous") val previous: String?,
    @SerializedName(value = "results") val results: List<BasePokemonDTO>,
)
