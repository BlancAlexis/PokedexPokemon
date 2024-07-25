package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.ui.graphics.Color
import com.example.pokedexpokemon.R

sealed class PokemonType(
    val color: Color,
    val icon: Int,
    val name: Int
) {
    class FIRE : PokemonType(Color(0xFFFFA726), R.drawable.pika_icone_petit, R.string.feu)
    class WATER : PokemonType(Color(0xFF42A5F5), R.drawable.pika_icone_petit, R.string.water)
    class GRASS : PokemonType(Color(0xFF66BB6A), R.drawable.pika_icone_petit, R.string.grass)
    class ELECTRIC : PokemonType(Color(0xFFFFEB3B), R.drawable.pika_icone_petit, R.string.electric)
    class PSYCHIC : PokemonType(Color(0xFFEC407A), R.drawable.pika_icone_petit, R.string.spychic)
    class ICE : PokemonType(Color(0xFF80DEEA), R.drawable.pika_icone_petit, R.string.ice)
    class DRAGON : PokemonType(Color(0xFF7B1FA2), R.drawable.pika_icone_petit, R.string.dragon)
    class GROUND : PokemonType(Color(0xFFD4A373), R.drawable.pika_icone_petit, R.string.ground)
    class ROCK : PokemonType(Color(0xFFBCAAA4), R.drawable.pika_icone_petit, R.string.rock)
    class FLYING : PokemonType(Color(0xFF81D4FA), R.drawable.pika_icone_petit, R.string.flying)
    class BUG : PokemonType(Color(0xFF8BC34A), R.drawable.pika_icone_petit, R.string.bug)
    class POISON : PokemonType(Color(0xFFBA68C8), R.drawable.pika_icone_petit, R.string.poison)
    class FAIRY : PokemonType(Color(0xFFF48FB1), R.drawable.pika_icone_petit, R.string.fairy)
    class GHOST : PokemonType(Color(0xFF757575), R.drawable.pika_icone_petit, R.string.ghost)
    class DARK : PokemonType(Color(0xFF212121), R.drawable.pika_icone_petit, R.string.dark)
    class STEEL : PokemonType(Color(0xFFB0BEC5), R.drawable.pika_icone_petit, R.string.steel)
    class NORMAL : PokemonType(Color(0xFFA1887F), R.drawable.pika_icone_petit, R.string.normal)
    class FIGHTING : PokemonType(Color(0xFFE57373), R.drawable.pika_icone_petit, R.string.fighting)
}


fun String.toPokemonType(): PokemonType =
    when (this.uppercase()) {
        "FIRE" -> PokemonType.FIRE()
        "WATER" -> PokemonType.WATER()
        "GRASS" -> PokemonType.GRASS()
        "ELECTRIC" -> PokemonType.ELECTRIC()
        "PSYCHIC" -> PokemonType.PSYCHIC()
        "ICE" -> PokemonType.ICE()
        "DRAGON" -> PokemonType.DRAGON()
        "GROUND" -> PokemonType.GROUND()
        "ROCK" -> PokemonType.ROCK()
        "FLYING" -> PokemonType.FLYING()
        "BUG" -> PokemonType.BUG()
        "POISON" -> PokemonType.POISON()
        "FAIRY" -> PokemonType.FAIRY()
        "GHOST" -> PokemonType.GHOST()
        "DARK" -> PokemonType.DARK()
        "STEEL" -> PokemonType.STEEL()
        "NORMAL" -> PokemonType.NORMAL()
        "FIGHTING" -> PokemonType.FIGHTING()
        else -> { PokemonType.FIRE()  }
    }