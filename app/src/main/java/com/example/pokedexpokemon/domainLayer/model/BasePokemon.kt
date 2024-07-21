package com.example.pokedexpokemon.domainLayer.model

import com.example.pokedexpokemon.dataLayer.di.PokedexService
import com.example.pokedexpokemon.dataLayer.dto.AbilityDTO
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.CriesDTO
import com.example.pokedexpokemon.dataLayer.dto.FormDTO
import com.example.pokedexpokemon.dataLayer.dto.GameIndexDTO
import com.example.pokedexpokemon.dataLayer.dto.InnerAbilityDTO
import com.example.pokedexpokemon.dataLayer.dto.InnerMoveDTO
import com.example.pokedexpokemon.dataLayer.dto.MoveDTO
import com.example.pokedexpokemon.dataLayer.dto.MoveLearnMethodDTO
import com.example.pokedexpokemon.dataLayer.dto.VersionDTO
import com.example.pokedexpokemon.dataLayer.dto.VersionGroupDTO
import com.example.pokedexpokemon.dataLayer.dto.VersionGroupDetailDTO
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import org.koin.core.component.KoinComponent


data class BasePokemon(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val cries: Cries,
    val form: List<Form>,
    val gameIndices: List<GameIndex>,
    val height: Int,
    //val heldItems: List<Any>, // Any because the field is empty in the JSON
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String
)

data class Ability(
    val ability: InnerAbilityDTO,
    val isHidden: Boolean,
    val slot: Int
)

data class InnerAbility(
    val name: String,
    val url: String
)

data class Cries(
    val latest: String,
    val legacy: String
)

data class Form(
    val name: String,
    val url: String
)

data class GameIndex(
    val gameIndex: Int,
    val version: VersionDTO
)

data class Version(
    val name: String,
    val url: String
)

data class Move(
    val move: InnerMoveDTO,
    val versionGroupDetailDTOS: List<VersionGroupDetailDTO>
)

data class InnerMove(
    val name: String,
    val url: String
)

data class VersionGroupDetail(
    val levelLearnedAt: Int,
    val moveLearnMethodDTO: MoveLearnMethodDTO,
    val versionGroupDTO: VersionGroupDTO
)

data class MoveLearnMethod(
    val name: String,
    val url: String
)

data class VersionGroup(
    val name: String,
    val url: String
)

fun BasePokemon.toDTO(): BasePokemonDTO {
    return BasePokemonDTO(
        abilities = abilities.map { it.toDTO() },
        baseExperience = baseExperience,
        criesDTO = cries.toDTO(),
        formDTOS = form.map { it.toDTO() },
        gameIndices = gameIndices.map { it.toDomain() },
        height = height,
        id = id,
        isDefault = isDefault,
        locationAreaEncounters = locationAreaEncounters,
        moves = moves.map { it.toDTO() },
        name = name
    )
}

private fun Ability.toDTO(): AbilityDTO {
    return AbilityDTO(
        ability = ability.toDTO(),
        isHidden = isHidden,
        slot = slot
    )
}

private fun InnerAbility.toDTO(): InnerAbilityDTO {
    return InnerAbilityDTO(name = name, url = url)
}

private fun Cries.toDTO(): CriesDTO {
    return CriesDTO(latest = latest, legacy = legacy)
}

private fun Form.toDTO(): FormDTO {
    return FormDTO(name = name, url = url)
}

private fun GameIndex.toDTO(): GameIndexDTO {
    return GameIndexDTO(gameIndex = gameIndex, version = version.toDTO())
}

private fun Version.toDTO(): VersionDTO {
    return VersionDTO(name = name, url = url)
}

private fun Move.toDTO(): MoveDTO {
    return Move(
        move = move.toDTO(),
        versionGroupDetailDTOS = versionGroupDetailDTOS.map { it.toDTO() }
    )
}

private fun InnerMove.toDTO(): InnerMoveDTO {
    return InnerMoveDTO(name = name, url = url)
}

private fun VersionGroupDetail.toDTO(): VersionGroupDetailDTO {
    return VersionGroupDetailDTO(
        levelLearnedAt = levelLearnedAt,
        moveLearnMethodDTO
}}





