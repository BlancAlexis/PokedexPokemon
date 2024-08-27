package com.example.pokedexpokemon.presentationLayer

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailPokemonViewModel
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsHost
import com.example.pokedexpokemon.presentationLayer.settings.SettingsHost
import com.example.pokedexpokemon.presentationLayer.teams.TeamViewHost
import com.example.pokedexpokemon.presentationLayer.theme.PokedexPokemonTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity: ComponentActivity(), KoinComponent {

    private val viewModel: ListDetailPokemonViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
            .apply {
                setKeepOnScreenCondition {
                    viewModel._isLoading.value
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


                    zoomX.start()
                    zoomY.start()
                }
                enableEdgeToEdge()
                setContent {
                    PokedexPokemonTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                var selectedItemIndex by rememberSaveable {
                                    mutableIntStateOf(0)
                                }
                                val windowWidthClass =
                                    currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
                                val navController = rememberNavController()
                                NavigationSuiteScaffold(
                                    navigationSuiteItems = {
                                        Screen.entries.forEachIndexed { index, screen ->
                                            item(
                                                selected = index == selectedItemIndex,
                                                onClick = {
                                                    selectedItemIndex = index
                                                    navController.navigate(Screen.entries[index].toString())
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
                                    NavHost(
                                        navController = navController,
                                        startDestination = Screen.HOME.toString()
                                    ) {
                                        composable(route = Screen.HOME.toString()) {
                                            ListDetailsHost(
                                                viewModel = viewModel,
                                                navigationEvent = {
                                                    when (it) {
                                                        NavigationEvent.Disconnect -> TODO()
                                                        is NavigationEvent.Navigate -> TODO()
                                                        is NavigationEvent.NavigateTo -> TODO()
                                                        NavigationEvent.PopStack -> TODO()
                                                    }
                                                },
                                                modifier = Modifier.padding(innerPadding),
                                            )
                                        }
                                        composable(route = Screen.TEAM.toString()) {
                                            TeamViewHost(
                                                modifier = Modifier.padding(innerPadding)
                                            )
                                        }
                                        composable(route = Screen.SETTINGS.toString()) {
                                            SettingsHost()
                                        }
                                    }
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
    TEAM("Team", Icons.Default.Search),
    SETTINGS("Settings", Icons.Default.Settings),
}

sealed interface NavigationEvent {
    data class NavigateTo(val menuId: Int) : NavigationEvent
    data class Navigate(val string: String) : NavigationEvent

    object Disconnect : NavigationEvent
    data object PopStack : NavigationEvent
}




