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
    @SerializedName("sprites") val sprites: Sprites
)



data class Sprites(
    val backDefault: String,
    val backFemale: String?, // nullable because it can be null in the JSON
    val backShiny: String,
    val backShinyFemale: String?,
    val frontDefault: String,
    val frontFemale: String?,
    val frontShiny: String,
    val frontShinyFemale: String?,
    val other: OtherSprites
)

data class OtherSprites(
    val dreamWorld: DreamWorldSprites,
    val home: HomeSprites,
    val `official-artwork`: OfficialArtworkSprites,
    val showdown: ShowdownSprites
)

data class DreamWorldSprites(
    val frontDefault: String,
    val frontFemale: String?
)

data class HomeSprites(
    val frontDefault: String,
    val frontFemale: String?,
    val frontShiny: String,
    val frontShinyFemale: String?
)

data class OfficialArtworkSprites(
    val frontDefault: String,
    val frontShiny: String
)

data class ShowdownSprites(
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

fun toBasePokemon(dto: BasePokemonDTO): BasePokemon {
    val abilities = dto.abilities.map { it.toAbility() }
    val gameIndices = dto.gameIndices.map { it.toGameIndex() }
    val moves = dto.moves.map { it.toMove() }
    val stats = dto.stats.map { it.toStat() }
    val sprites = dto.sprites.toSprites()
    return BasePokemon(
        abilities = abilities,
        baseExperience = dto.baseExperience,
        roar = Roar(dto.criesDTO.latest, dto.criesDTO.legacy),
        gameIndices = gameIndices,
        height = dto.height,
        id = dto.id,
        moves = moves,
        name = dto.name,
        stats = stats,
        sprites = sprites
    )
}

private fun Sprites.toSprites(): Sprites {
    return Sprites(
        backDefault = backDefault,
        frontDefault = frontDefault,
        backFemale = backFemale,
        backShiny = backShiny,
        backShinyFemale = backShinyFemale,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale,
        other = other.toOtherSprites()
    )
}

private fun OtherSprites.toOtherSprites(): OtherSprites {
    return OtherSprites(
        dreamWorld = dreamWorld.toDreamWorldSprites(),
        home = home.toHomeSprites(),
        officialArtwork = officialArtwork.toOfficialArtworkSprites(),
        showdown = showdown.toShowdownSprites()
    )
}

private fun DreamWorldSprites.toDreamWorldSprites(): DreamWorldSprites {
    return DreamWorldSprites(frontDefault = frontDefault, frontFemale = frontFemale)
}

private fun HomeSprites.toHomeSprites(): HomeSprites {
    return HomeSprites(
        frontDefault = frontDefault,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale
    )
}

private fun OfficialArtworkSprites.toOfficialArtworkSprites(): OfficialArtworkSprites {
    return OfficialArtworkSprites(frontDefault = frontDefault, frontShiny = frontShiny)
}

private fun ShowdownSprites.toShowdownSprites(): ShowdownSprites {
    return ShowdownSprites(
        backDefault = backDefault,
        backFemale = backFemale,
        backShiny = backShiny,
        backShinyFemale = backShinyFemale,
        frontDefault = frontDefault,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale
    )
}

private fun Stat.toStat(): Stat {
    return Stat(baseStat = baseStat, effort = effort, stat = stat.toInnerStat())
}

private fun InnerStat.toInnerStat(): InnerStat {
    return InnerStat(name = name, url = url)
}

private fun AbilityDTO.toAbility(): Ability {
    return Ability(ability = ability.toInnerAbility(), isHidden = isHidden, slot = slot)
}

private fun InnerAbilityDTO.toInnerAbility(): InnerAbility {
    return InnerAbility(name = name, url = url)
}

private fun GameIndexDTO.toGameIndex(): GameIndex {
    return GameIndex(gameIndex = gameIndex, version = version.toVersion())
}

private fun VersionDTO.toVersion(): Version {
    return Version(name = name, url = url)
}

private fun MoveDTO.toMove(): Move {
    return Move(move = move.toInnerMove(), versionGroupDetailDTOS = versionGroupDetailDTOS.map { it.toVersionGroupDetail() })
}

private fun InnerMoveDTO.toInnerMove(): InnerMove {
    return InnerMove(name = name, url = url)
}

private fun VersionGroupDetailDTO.toVersionGroupDetail(): VersionGroupDetail {
    return VersionGroupDetail(
        levelLearnedAt = levelLearnedAt,
        versionGroup = versionGroupDTO.toVersionGroup()
    )
}

private fun VersionGroupDTO.toVersionGroup(): VersionGroup {
    return VersionGroup(name = name, url = url)
}