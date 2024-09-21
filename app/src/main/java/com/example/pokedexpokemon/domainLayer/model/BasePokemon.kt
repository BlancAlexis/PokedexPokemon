import com.example.pokedexpokemon.domainLayer.model.PokemonType
import java.io.Serializable

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
    val type: List<PokemonType>,
)

data class Stat(
    val name: String, val baseStat: Int, val effort: Int
) : Serializable


data class Ability(
    val abilityName: String, val isHidden: Boolean
): Serializable


data class Roar(
    val urlLastestRoar: String
)


data class GameIndex(
    val gameIndex: Int, val version: Version
): Serializable

data class Version(
    val name: String // Avoir un logo ici?
) : Serializable

data class Move(
    val moveName: String, val levelLearnedAt: Int
): Serializable

// Move variant entre jeux

data class Sprites(
    val baseSprite: String,
    val backDefault: String,
    val frontDefault: String,
): Serializable



