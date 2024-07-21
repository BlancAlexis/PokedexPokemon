data class BasePokemon(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val roar: Roar,
    val gameIndices: List<GameIndex>,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val stats: List<Stat>,
    val sprites: Sprites
)

data class Sprites(
    val backDefault: String,
    val frontDefault: String,
)

data class Stat(
    val baseStat: Int, val effort: Int, val stat: InnerStat
)

data class InnerStat(
    val name: String, val url: String
)

data class Ability(
    val ability: InnerAbility, val isHidden: Boolean, val slot: Int
)

data class InnerAbility(
    val name: String, val url: String
)

data class Roar(
    val latest: String, val legacy: String
)


data class GameIndex(
    val gameIndex: Int, val version: Version
)

data class Version(
    val name: String, val url: String
)

data class Move(
    val move: InnerMove, val versionGroupDetailDTOS: List<VersionGroupDetail>
)

data class InnerMove(
    val name: String, val url: String
)

data class VersionGroupDetail(
    val levelLearnedAt: Int, val versionGroup: VersionGroup
)

data class VersionGroup(
    val name: String, val url: String
)

