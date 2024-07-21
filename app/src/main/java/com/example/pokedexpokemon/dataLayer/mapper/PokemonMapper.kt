package com.example.pokedexpokemon.dataLayer.mapper

import Ability
import BasePokemon
import GameIndex
import InnerMove
import Move
import VersionGroup
import VersionGroupDetail
import com.example.pokedexpokemon.dataLayer.dto.AbilityDTO
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.GameIndexDTO
import com.example.pokedexpokemon.dataLayer.dto.InnerStat
import com.example.pokedexpokemon.dataLayer.dto.MoveDTO
import com.example.pokedexpokemon.dataLayer.dto.Stat
import com.example.pokedexpokemon.dataLayer.dto.VersionGroupDetailDTO
import okhttp3.internal.Version

object PokemonMapper {

    fun toDomainPokemon(dto: BasePokemonDTO): BasePokemon =
        return BasePokemon(
            abilities = dto.abilities.map { toDomainAbility(it) },
            baseExperience = dto.baseExperience,
            roar = Roar(latest = dto.criesDTO.latest, roarLegacy = dto.criesDTO.legacy),
            gameIndices = dto.gameIndices.map { toDomainGameIndex(it) },
            height = dto.height,
            id = dto.id,
            moves = dto.moves.map { toDomainMove(it) },
            name = dto.name,
            stats = dto.stats.map { toDomainStat(it) },
            sprites = Sprites(dto.sprites.backDefault, dto.sprites.frontDefault)
        )
    }

    private fun toDomainAbility(dto: AbilityDTO): Ability {
        return Ability(
            ability = InnerAbility(dto.ability.name, dto.ability.url),
            isHidden = dto.isHidden,
            slot = dto.slot
        )
    }

    private fun toDomainGameIndex(dto: GameIndexDTO): GameIndex {
        return GameIndex(
            gameIndex = dto.gameIndex,
            version = Version(dto.version.name, dto.version.url)
        )
    }

    private fun toDomainMove(dto: MoveDTO): Move {
        return Move(
            move = InnerMove(dto.move.name, dto.move.url),
            versionGroupDetails = dto.versionGroupDetailDTOS.map { toDomainVersionGroupDetail(it) }
        )
    }

    private fun toDomainVersionGroupDetail(dto: VersionGroupDetailDTO): VersionGroupDetail {
        return VersionGroupDetail(
            levelLearnedAt = dto.levelLearnedAt,
            versionGroup = VersionGroup(dto.versionGroupDTO.name, dto.versionGroupDTO.url)
        )
    }

    private fun toDomainStat(dto: Stat): Stat {
        return Stat(
            baseStat = dto.baseStat,
            effort = dto.effort,
            stat = InnerStat(dto.stat.name, dto.stat.url)
        )
    }
}