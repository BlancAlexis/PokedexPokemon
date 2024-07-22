package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.ui.graphics.Color
import com.example.pokedexpokemon.R

sealed class PokemonType(
    val color: Color,
    val icon: Int,
    val name: Int
) {
    class FIRE : PokemonType(Color.Yellow, R.drawable.pika_icone_petit, R.string.feu)
    class WATER : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.water)
    class GRASS : PokemonType(Color.Green, R.drawable.pika_icone_petit, R.string.grass)
    class ELECTRIC : PokemonType(Color.Yellow, R.drawable.pika_icone_petit, R.string.electric)
    class PSYCHIC : PokemonType(Color.Magenta, R.drawable.pika_icone_petit, R.string.spychic)
    class ICE : PokemonType(Color.Cyan, R.drawable.pika_icone_petit, R.string.ice)
    class DRAGON : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.dragon)
    class GROUND : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.ground)
    class ROCK : PokemonType(Color.Gray, R.drawable.pika_icone_petit, R.string.rock)
    class FLYING : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.flying)
    class BUG : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.bug)
    class POISON : PokemonType(Color.Magenta, R.drawable.pika_icone_petit, R.string.poison)
    class FAIRY : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.fairy)
    class GHOST : PokemonType(Color.DarkGray, R.drawable.pika_icone_petit, R.string.ghost)
    class DARK : PokemonType(Color.Black, R.drawable.pika_icone_petit, R.string.dark)
    class STEEL : PokemonType(Color.LightGray, R.drawable.pika_icone_petit, R.string.steel)
    class NORMAL : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.normal)
    class FIGHTING : PokemonType(Color.Red, R.drawable.pika_icone_petit, R.string.fighting)
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