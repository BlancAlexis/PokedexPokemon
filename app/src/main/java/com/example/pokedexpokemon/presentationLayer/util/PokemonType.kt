package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.ui.graphics.Color
import com.example.pokedexpokemon.R

sealed class PokemonType(
    val color: Color,
    val icon: Int,
    val name: Int
) {
    class FIRE : PokemonType(Color.Yellow, R.drawable.pika_icone_petit, R.string.app_name)
    class WATER : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class GRASS : PokemonType(Color.Green, R.drawable.pika_icone_petit, R.string.app_name)
    class ELECTRIC : PokemonType(Color.Yellow, R.drawable.pika_icone_petit, R.string.app_name)
    class PSYCHIC : PokemonType(Color.Magenta, R.drawable.pika_icone_petit, R.string.app_name)
    class ICE : PokemonType(Color.Cyan, R.drawable.pika_icone_petit, R.string.app_name)
    class DRAGON : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class GROUND : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class ROCK : PokemonType(Color.Gray, R.drawable.pika_icone_petit, R.string.app_name)
    class FLYING : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class BUG : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class POISON : PokemonType(Color.Magenta, R.drawable.pika_icone_petit, R.string.app_name)
    class FAIRY : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class GHOST : PokemonType(Color.DarkGray, R.drawable.pika_icone_petit, R.string.app_name)
    class DARK : PokemonType(Color.Black, R.drawable.pika_icone_petit, R.string.app_name)
    class STEEL : PokemonType(Color.LightGray, R.drawable.pika_icone_petit, R.string.app_name)
    class NORMAL : PokemonType(Color.Blue, R.drawable.pika_icone_petit, R.string.app_name)
    class FIGHTING : PokemonType(Color.Red, R.drawable.pika_icone_petit, R.string.app_name)
}