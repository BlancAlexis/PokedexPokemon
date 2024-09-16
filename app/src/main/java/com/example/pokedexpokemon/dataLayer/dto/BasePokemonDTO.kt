package com.example.pokedexpokemon.dataLayer.dto


import com.google.gson.annotations.SerializedName


data class BasePokemonDTO(
    @SerializedName("abilities") val abilities: List<AbilityDTO>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("cries") val criesDTO: CriesDTO,
    @SerializedName("forms") val formDTOS: List<FormDTO>,
    @SerializedName("game_indices") val gameIndices: List<GameIndexDTO>,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    //val heldItems: List<Any>, // Any because the field is empty in the JSON
    @SerializedName("id") val id: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<MoveDTO>,
    @SerializedName("name") val name: String,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("sprites") val sprites: SpritesDTO,
    @SerializedName("types") val types: List<TypeSlot>
)


data class SpritesDTO(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?, // nullable because it can be null in the JSON
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?,
    @SerializedName("other") val other: OtherSpritesDTO
)

data class OtherSpritesDTO(
    @SerializedName("dream_world") val dreamWorld: DreamWorldSpritesDTO?,
    @SerializedName("home") val home: HomeSpritesDTO?,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkSpritesDTO?,
    @SerializedName("showdown") val showdown: ShowdownSpritesDTO?
)

data class DreamWorldSpritesDTO(
    val frontDefault: String?,
    val frontFemale: String?
)

data class HomeSpritesDTO(
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class OfficialArtworkSpritesDTO(
    val frontDefault: String?,
    val frontShiny: String?
)

data class ShowdownSpritesDTO(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_female") val backFemale: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_female") val frontFemale: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class Stat(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: InnerStat
)

data class InnerStat(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
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
    @SerializedName("legacy") val legacy: String
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

data class PokemonTypeDTO(
    val name: String,
    val url: String
)

data class TypeSlot(
    val slot: Int,
    val type: PokemonTypeDTO
)
