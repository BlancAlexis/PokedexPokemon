import com.example.pokedexpokemon.dataLayer.dto.BasePokemonDTO
import com.example.pokedexpokemon.dataLayer.dto.SpritesDTO
import com.example.pokedexpokemon.domainLayer.model.PokemonType
import com.example.pokedexpokemon.presentationLayer.util.toPokemonType

data class BasePokemon(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val roar: Roar,
    val gameIndices: List<GameIndex>,
    val height: Int,
    val weight: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val stats: List<Stat>,
    val sprites: Sprites,
    val type : List<PokemonType>,
){

}

data class Stat(
    val name: String, val baseStat: Int, val effort: Int
)


data class Ability(
    val abilityName: String, val isHidden: Boolean
)


data class Roar(
    val urlLastestRoar: String
)


data class GameIndex(
    val gameIndex: Int, val version: Version
)

data class Version(
    val name: String // Avoir un logo ici?
)

data class Move(
    val moveName: String, val levelLearnedAt: Int
)

// Move variant entre jeux

data class Sprites(
    val baseSprite : String,
    val backDefault: String,
    val frontDefault: String,
)
fun BasePokemonDTO.toDomain(): BasePokemon {
    return BasePokemon(
        abilities = abilities.map { Ability(it.ability.name, it.isHidden) },
        baseExperience = baseExperience,
        roar = Roar(criesDTO.latest),
        gameIndices = gameIndices.map { GameIndex(it.gameIndex, Version(it.version.name)) },
        height = height,
        id = id,
        moves = moves.map { Move(it.move.name, it.versionGroupDetailDTOS.first().levelLearnedAt) }.toList(),
        name = name,
        stats = stats.map { Stat(it.stat.name, it.baseStat, it.effort) },
        weight = weight,
        type = types.map { PokemonType.valueOf(it.type.name.uppercase()) }.toList(),
        sprites = sprites.toDomain()
    )
}

fun SpritesDTO.toDomain() = Sprites(
    baseSprite = this.other.officialArtwork?.frontDefault ?: "",
    backDefault = this.other.showdown?.backDefault ?: "",
    frontDefault = this.other.showdown?.frontDefault ?: "" // TODO gestion erreur
)


