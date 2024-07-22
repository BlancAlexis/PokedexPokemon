package com.example.pokedexpokemon.presentationLayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailLayout
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.theme.PokedexPokemonTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val getList by inject<GetPokemonList>()
    val getPokemon by inject<GetPokemon>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        GlobalScope.launch {
            when(val resullt = getList.invoke()){
                is Ressource.Error -> { println("result.data ${resullt.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> println("result.data ${resullt.data}")
            }
        }
        enableEdgeToEdge()
        setContent {

            PokedexPokemonTheme {

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        var selectedItemIndex by remember {
                            mutableIntStateOf(0)
                        }
                        val windowWidthClass =
                            currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
                        NavigationSuiteScaffold(
                            navigationSuiteItems = {
                                Screen.entries.forEachIndexed { index, screen ->
                                    item(
                                        selected = index == selectedItemIndex,
                                        onClick = {
                                            selectedItemIndex = index
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = screen.icon,
                                                contentDescription = screen.title
                                            )
                                        },
                                        label = {
                                            Text(text = screen.title)
                                        }
                                    )
                                }
                            },
                            layoutType = if (windowWidthClass == WindowWidthSizeClass.EXPANDED) {
                                NavigationSuiteType.NavigationDrawer
                            } else {
                                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                                    currentWindowAdaptiveInfo()
                                )
                            }
                        ) {
                            val navController = rememberNavController()
                            NavHost(navController = navController, startDestination = "listDetails"){
                                composable(route = "listDetails"){
                                    ListDetailLayout(
                                        navigationEvent = {
                                            when(it){
                                                NavigationEvent.Disconnect -> TODO()
                                                is NavigationEvent.Navigate -> TODO()
                                                is NavigationEvent.NavigateTo -> TODO()
                                                NavigationEvent.PopStack -> TODO()
                                            }
                                        },
                                        modifier = Modifier.padding(innerPadding),
                                        uiState = listOf(
                                            ListDetailsPokemonUiState(
                                                name = "Absol",
                                                type = listOf("Ténèbre", "Psy"),
                                                id = 5
                                            )
                                        )
                                    )
                                }
                            }
                    }

                }


            }
        }
    }
}

    enum class Screen(val title: String, val icon: ImageVector) {
        HOME("Home", Icons.Default.Home),
        SEARCH("Search", Icons.Default.Search),
        SETTINGS("Settings", Icons.Default.Settings),
    }

sealed interface NavigationEvent {
    data class NavigateTo(val menuId: Int) : NavigationEvent
    data class Navigate(val string: String) : NavigationEvent

    object Disconnect : NavigationEvent
    data object PopStack: NavigationEvent
}
//sealed class PokemonType(
//    val color: Int,
//    val icon: Int,
//    val name: String
//) {
//    class FIRE : PokemonType(Color.Orange, R.drawable.fire_icon, R.string.fire)
//    class WATER : PokemonType(Color.Blue, R.drawable.water_icon, R.string.water)
//    class GRASS : PokemonType(Color.Green, R.drawable.grass_icon, R.string.grass)
//    class ELECTRIC : PokemonType(Color.Yellow, R.drawable.electric_icon, R.string.electric)
//    class PSYCHIC : PokemonType(Color.Purple, R.drawable.psychic_icon, R.string.psychic)
//    class ICE : PokemonType(Color.Cyan, R.drawable.ice_icon, R.string.ice)
//    class DRAGON : PokemonType(Color.BlueViolet, R.drawable.dragon_icon, R.string.dragon)
//    // Ajoutez d'autres types de Pokémon ici
//    class GROUND : PokemonType(Color.Brown, R.drawable.ground_icon, R.string.ground)
//    class ROCK : PokemonType(Color.Gray, R.drawable.rock_icon, R.string.rock)
//    class FLYING : PokemonType(Color.SkyBlue, R.drawable.flying_icon, R.string.flying)
//    class BUG : PokemonType(Color.Lime, R.drawable.bug_icon, R.string.bug)
//    class POISON : PokemonType(Color.Magenta, R.drawable.poison_icon, R.string.poison)
//    class FAIRY : PokemonType(Color.Pink, R.drawable.fairy_icon, R.string.fairy)
//    class GHOST : PokemonType(Color.DarkGray, R.drawable.ghost_icon, R.string.ghost)
//    class DARK : PokemonType(Color.Black, R.drawable.dark_icon, R.string.dark)
//    class STEEL : PokemonType(Color.LightGray, R.drawable.steel_icon, R.string.steel)
//    class NORMAL : PokemonType(Color.Beige, R.drawable.normal_icon, R.string.normal)
//    class FIGHTING : PokemonType(Color.Red, R.drawable.fighting_icon, R.string.fighting)
//}



