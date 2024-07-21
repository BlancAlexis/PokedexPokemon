package com.example.pokedexpokemon.dataLayer.dto


import com.google.gson.annotations.SerializedName


import androidx.compose.runtime.Immutable


import kotlin.random.Random


@Immutable
data class BasePokemonDTO(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "height") val height: Int,
    @SerializedName(value = "weight") val weight: Int,
    @SerializedName(value = "base_experience") val experience: Int,
    @SerializedName(value = "types") val types: List<TypeResponse>,
    @SerializedName(value = "stats") val stats: List<StatsResponse>,
    val exp: Int = Random.nextInt(MAX_EXP),
) {
    val hp: Int by lazy {
        stats.firstOrNull { it.stat.name == "hp" }?.baseStat ?: Random.nextInt(MAX_HP)
    }
    val attack: Int by lazy {
        stats.firstOrNull { it.stat.name == "attack" }?.baseStat ?: Random.nextInt(MAX_ATTACK)
    }
    val defense: Int by lazy {
        stats.firstOrNull { it.stat.name == "defense" }?.baseStat ?: Random.nextInt(MAX_DEFENSE)
    }
    val speed: Int by lazy {
        stats.firstOrNull { it.stat.name == "speed" }?.baseStat ?: Random.nextInt(MAX_SPEED)
    }

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = " $hp/$MAX_HP"
    fun getAttackString(): String = " $attack/$MAX_ATTACK"
    fun getDefenseString(): String = " $defense/$MAX_DEFENSE"
    fun getSpeedString(): String = " $speed/$MAX_SPEED"
    fun getExpString(): String = " $exp/$MAX_EXP"


    data class TypeResponse(
        @SerializedName(value = "slot") val slot: Int,
        @SerializedName(value = "type") val type: Type,
    )


    data class StatsResponse(
        @SerializedName(value = "base_stat") val baseStat: Int,
        @SerializedName(value = "effort") val effort: Int,
        @SerializedName(value = "stat") val stat: Stat,
    )


    data class Stat(
        @SerializedName(value = "name") val name: String,
    )

    data class Type(
        @SerializedName(value = "name") val name: String,
    )

    companion object {
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 300
        const val MAX_EXP = 1000
    }
}