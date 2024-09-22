package com.example.pokedexpokemon.presentationLayer.listDetailScreen

import BasePokemon
import com.example.pokedexpokemon.presentationLayer.util.toPokemonType

object BasePokemonMapper {
    fun BasePokemon.toUiState(): PokemonUiState = PokemonUiState(
        name = this.name.replaceFirstChar { char -> char.uppercase() },
        weight = this.weight,
        height = this.height,
        baseExperience = this.baseExperience,
        type = this.type.map { it.name.toPokemonType() }.toList(),
        abilities = this.abilities,
        gameIndices = this.gameIndices,
        nationalIndices = this.id,
        sprites = this.sprites,
        talent = this.abilities,
        roar = this.roar.urlLastestRoar,
        moves = this.moves.filter { it.levelLearnedAt != 0 }.sortedBy { it.levelLearnedAt },
        stats = this.stats
    )
}