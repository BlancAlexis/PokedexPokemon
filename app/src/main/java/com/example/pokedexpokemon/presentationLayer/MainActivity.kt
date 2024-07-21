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
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailLayout
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsPokemonUiState
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
            when(val resullt = getPokemon.invoke("2")){
                is Ressource.Error -> { println("result.data suicde ${resullt.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> {
                    println("result.data suicde ${resullt.data}")
                }
            }
        }
        GlobalScope.launch {
            when(val resullt = getList.invoke()){
                is Ressource.Error -> { println("result.data ${resullt.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> println("result.data ${resullt.data}")
            }
        }
           /* .apply {
            setKeepOnScreenCondition {
                //Check auto co puis mais ok

                false
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }
                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

            }
        }*/

        enableEdgeToEdge()


        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var selectedItemIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val windowWidthClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
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
                        layoutType = if(windowWidthClass == WindowWidthSizeClass.EXPANDED) {
                            NavigationSuiteType.NavigationDrawer
                        } else {
                            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                                currentWindowAdaptiveInfo()
                            )
                        }
                    ) {
                    ListDetailLayout(
                        modifier = Modifier.padding(innerPadding),
                        listOf( ListDetailsPokemonUiState(name = "Absol", type = listOf("Ténèbre","Psy") , id=5)
                        )
                    )
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



