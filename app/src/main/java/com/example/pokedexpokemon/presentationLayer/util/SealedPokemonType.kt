package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.ui.graphics.Color
import com.example.pokedexpokemon.R
import java.io.Serializable

sealed class SealedPokemonType(
    val color: Color,
    val icon: Int,
    val name: Int
) : Serializable {
    class FIRE : SealedPokemonType(Color(0xFFFFA726), R.drawable.fire, R.string.feu)
    class WATER : SealedPokemonType(Color(0xFF42A5F5), R.drawable.water, R.string.water)
    class GRASS : SealedPokemonType(Color(0xFF66BB6A), R.drawable.grass, R.string.grass)
    class ELECTRIC : SealedPokemonType(Color(0xFFFFEB3B), R.drawable.electric, R.string.electric)
    class PSYCHIC : SealedPokemonType(Color(0xFFEC407A), R.drawable.psychic, R.string.spychic)
    class ICE : SealedPokemonType(Color(0xFF80DEEA), R.drawable.ice, R.string.ice)
    class DRAGON : SealedPokemonType(Color(0xFF7B1FA2), R.drawable.dragon, R.string.dragon)
    class GROUND : SealedPokemonType(Color(0xFFD4A373), R.drawable.ground, R.string.ground)
    class ROCK : SealedPokemonType(Color(0xFFBCAAA4), R.drawable.rock, R.string.rock)
    class FLYING : SealedPokemonType(Color(0xFF81D4FA), R.drawable.flying, R.string.flying)
    class BUG : SealedPokemonType(Color(0xFF8BC34A), R.drawable.bug, R.string.bug)
    class POISON : SealedPokemonType(Color(0xFFBA68C8), R.drawable.poison, R.string.poison)
    class FAIRY : SealedPokemonType(Color(0xFFF48FB1), R.drawable.fairy, R.string.fairy)
    class GHOST : SealedPokemonType(Color(0xFF757575), R.drawable.ghost, R.string.ghost)
    class DARK : SealedPokemonType(Color(0xFF212121), R.drawable.dark, R.string.dark)
    class STEEL : SealedPokemonType(Color(0xFFB0BEC5), R.drawable.steel, R.string.steel)
    class NORMAL : SealedPokemonType(Color(0xFFA1887F), R.drawable.normal, R.string.normal)
    class FIGHTING : SealedPokemonType(Color(0xFFE57373), R.drawable.fighting, R.string.fighting)
}


fun String.toPokemonType(): SealedPokemonType =
    when (this.uppercase()) {
        "FIRE" -> SealedPokemonType.FIRE()
        "WATER" -> SealedPokemonType.WATER()
        "GRASS" -> SealedPokemonType.GRASS()
        "ELECTRIC" -> SealedPokemonType.ELECTRIC()
        "PSYCHIC" -> SealedPokemonType.PSYCHIC()
        "ICE" -> SealedPokemonType.ICE()
        "DRAGON" -> SealedPokemonType.DRAGON()
        "GROUND" -> SealedPokemonType.GROUND()
        "ROCK" -> SealedPokemonType.ROCK()
        "FLYING" -> SealedPokemonType.FLYING()
        "BUG" -> SealedPokemonType.BUG()
        "POISON" -> SealedPokemonType.POISON()
        "FAIRY" -> SealedPokemonType.FAIRY()
        "GHOST" -> SealedPokemonType.GHOST()
        "DARK" -> SealedPokemonType.DARK()
        "STEEL" -> SealedPokemonType.STEEL()
        "NORMAL" -> SealedPokemonType.NORMAL()
        "FIGHTING" -> SealedPokemonType.FIGHTING()
        else -> {
            SealedPokemonType.FIRE()
        }
    }