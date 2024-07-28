package com.example.pokedexpokemon.domainLayer.card_mapper

import Ability
import BasePokemon
import GameIndex
import Move
import Roar
import Sprites
import Stat
import Version
import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.SpritesDTO
import com.example.pokedexpokemon.domainLayer.model.PokemonType

object PokemonMapper {

    fun BasePokemonDTO.toDomain(): BasePokemon {
        return BasePokemon(
            abilities = abilities.map { Ability(it.ability.name, it.isHidden) },
            baseExperience = baseExperience,
            roar = Roar(criesDTO.latest),
            gameIndices = gameIndices.map { GameIndex(it.gameIndex, Version(it.version.name)) },
            height = height,
            id = id,
            moves = moves.map {
                Move(
                    it.move.name,
                    it.versionGroupDetailDTOS.first().levelLearnedAt
                )
            }.toList(),
            name = name,
            stats = stats.map { Stat(it.stat.name, it.baseStat, it.effort) },
            weight = weight,
            type = types.map { PokemonType.valueOf(it.type.name.uppercase()) }.toList(),
            sprites = sprites.toDomain()
        )
    }

    fun SpritesDTO.toDomain() = Sprites(
        baseSprite = this.frontDefault ?: "",
        backDefault = this.other.showdown?.backDefault ?: "",
        frontDefault = this.other.showdown?.frontDefault ?: "" // TODO gestion erreur
    )
}