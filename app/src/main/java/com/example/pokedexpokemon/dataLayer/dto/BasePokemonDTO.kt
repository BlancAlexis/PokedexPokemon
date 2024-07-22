package com.example.pokedexpokemon.dataLayer.dto


import com.google.gson.annotations.SerializedName


import androidx.compose.runtime.Immutable


import kotlin.random.Random


data class BasePokemonDTO(
    @SerializedName("abilities") val abilities: List<AbilityDTO>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("cries") val criesDTO: CriesDTO,
    @SerializedName("forms") val formDTOS: List<FormDTO>,
    @SerializedName("game_indices") val gameIndices: List<GameIndexDTO>,
    @SerializedName("height") val height: Int,
    //val heldItems: List<Any>, // Any because the field is empty in the JSON
    @SerializedName("id")  val id: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<MoveDTO>,
    @SerializedName("name") val name: String,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("sprites") val sprites: SpritesDTO
)



data class SpritesDTO(
    val backDefault: String,
    val backFemale: String?, // nullable because it can be null in the JSON
    val backShiny: String,
    val backShinyFemale: String?,
    val frontDefault: String,
    val frontFemale: String?,
    val frontShiny: String,
    val frontShinyFemale: String?,
    val other: OtherSpritesDTO
)

data class OtherSpritesDTO(
    val dreamWorld: DreamWorldSpritesDTO,
    val home: HomeSpritesDTO,
    val `official-artwork`: OfficialArtworkSpritesDTO,
    val showdown: ShowdownSpritesDTO
)

data class DreamWorldSpritesDTO(
    val frontDefault: String,
    val frontFemale: String?
)

data class HomeSpritesDTO(
    val frontDefault: String,
    val frontFemale: String?,
    val frontShiny: String,
    val frontShinyFemale: String?
)

data class OfficialArtworkSpritesDTO(
    val frontDefault: String,
    val frontShiny: String
)

data class ShowdownSpritesDTO(
    val backDefault: String,
    val backFemale: String?,
    val backShiny: String,
    val backShinyFemale: String?,
    val frontDefault: String,
    val frontFemale: String?,
    val frontShiny: String,
    val frontShinyFemale: String?
)
data class Stat(
    val baseStat: Int,
    val effort: Int,
    val stat: InnerStat
)

data class InnerStat(
    val name: String,
    val url: String
)

data class AbilityDTO(
    @SerializedName("ability") val ability: InnerAbilityDTO,
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("slot") val slot: Int
)

data class InnerAbilityDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class CriesDTO(
    @SerializedName("latest") val latest: String,
    @SerializedName("legacy")  val legacy: String
)

data class FormDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class GameIndexDTO(
    @SerializedName("game_index") val gameIndex: Int,
    @SerializedName("version") val version: VersionDTO
)

data class VersionDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class MoveDTO(
    @SerializedName("move") val move: InnerMoveDTO,
    @SerializedName("version_group_details") val versionGroupDetailDTOS: List<VersionGroupDetailDTO>
)

data class InnerMoveDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class VersionGroupDetailDTO(
    @SerializedName("level_learned_at") val levelLearnedAt: Int,
    @SerializedName("move_learn_method") val moveLearnMethodDTO: MoveLearnMethodDTO,
    @SerializedName("version_group") val versionGroupDTO: VersionGroupDTO
)

data class MoveLearnMethodDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class VersionGroupDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
