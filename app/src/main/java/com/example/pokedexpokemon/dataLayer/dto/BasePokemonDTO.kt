package com.example.pokedexpokemon.dataLayer.dto

import com.example.pokedexpokemon.domainLayer.model.Ability
import com.example.pokedexpokemon.domainLayer.model.BasePokemon
import com.example.pokedexpokemon.domainLayer.model.Cries
import com.example.pokedexpokemon.domainLayer.model.Form
import com.example.pokedexpokemon.domainLayer.model.GameIndex
import com.example.pokedexpokemon.domainLayer.model.InnerAbility
import com.example.pokedexpokemon.domainLayer.model.InnerMove
import com.example.pokedexpokemon.domainLayer.model.Move
import com.example.pokedexpokemon.domainLayer.model.MoveLearnMethod
import com.example.pokedexpokemon.domainLayer.model.Version
import com.example.pokedexpokemon.domainLayer.model.VersionGroup
import com.example.pokedexpokemon.domainLayer.model.VersionGroupDetail

data class BasePokemonDTO(
    val abilities: List<AbilityDTO>,
    val baseExperience: Int,
    val criesDTO: CriesDTO,
    val formDTOS: List<FormDTO>,
    val gameIndices: List<GameIndexDTO>,
    val height: Int,
    //val heldItems: List<Any>, // Any because the field is empty in the JSON
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<MoveDTO>,
    val name: String
)

data class AbilityDTO(
    val ability: InnerAbilityDTO,
    val isHidden: Boolean,
    val slot: Int
)

data class InnerAbilityDTO(
    val name: String,
    val url: String
)

data class CriesDTO(
    val latest: String,
    val legacy: String
)

data class FormDTO(
    val name: String,
    val url: String
)

data class GameIndexDTO(
    val gameIndex: Int,
    val version: VersionDTO
)

data class VersionDTO(
    val name: String,
    val url: String
)

data class MoveDTO(
    val move: InnerMoveDTO,
    val versionGroupDetailDTOS: List<VersionGroupDetailDTO>
)

data class InnerMoveDTO(
    val name: String,
    val url: String
)

data class VersionGroupDetailDTO(
    val levelLearnedAt: Int,
    val moveLearnMethodDTO: MoveLearnMethodDTO,
    val versionGroupDTO: VersionGroupDTO
)

data class MoveLearnMethodDTO(
    val name: String,
    val url: String
)

data class VersionGroupDTO(
    val name: String,
    val url: String
)

fun BasePokemonDTO.toDomain(): BasePokemon {
    return BasePokemon(
        abilities = abilities.map { it.toDomain() },
        baseExperience = baseExperience,
        cries = criesDTO.toDomain(),
        form = formDTOS.map { it.toDomain() },
        gameIndices = gameIndices.map { it.toDomain() },
        height = height,
        id = id,
        isDefault = isDefault,
        locationAreaEncounters = locationAreaEncounters,
        moves = moves.map { it.toDomain() },
        name = name
    )
}

private fun AbilityDTO.toDomain(): Ability {
    return Ability(
        ability = ability.toDomain(),
        isHidden = isHidden,
        slot = slot
    )
}

private fun InnerAbilityDTO.toDomain(): InnerAbility {
    return InnerAbility(name = name, url = url)
}

private fun CriesDTO.toDomain(): Cries {
    return Cries(latest = latest, legacy = legacy)
}

private fun FormDTO.toDomain(): Form {
    return Form(name = name, url = url)
}

private fun GameIndexDTO.toDomain(): GameIndex {
    return GameIndex(gameIndex = gameIndex, version = version.toDomain())
}

private fun VersionDTO.toDomain(): Version {
    return Version(name = name, url = url)
}

private fun MoveDTO.toDomain(): Move {
    return Move(
        move = move.toDomain(),
        versionGroupDetailDTOS = versionGroupDetailDTOS.map { it.toDomain() }
    )
}

private fun InnerMoveDTO.toDomain(): InnerMove {
    return InnerMove(name = name, url = url)
}

private fun VersionGroupDetailDTO.toDomain(): VersionGroupDetail {
    return VersionGroupDetail(
        levelLearnedAt = levelLearnedAt,
        moveLearnMethodDTO = moveLearnMethodDTO.toDomain(),
        versionGroupDTO = versionGroupDTO.toDomain()
    )
}

private fun MoveLearnMethodDTO.toDomain(): MoveLearnMethod {
    return MoveLearnMethod(name = name, url = url)
}

private fun VersionGroupDTO.toDomain(): VersionGroup {
    return VersionGroup(name = name, url = url)
}
