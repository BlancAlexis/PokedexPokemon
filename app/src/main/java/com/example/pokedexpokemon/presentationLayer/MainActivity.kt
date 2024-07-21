package com.example.pokedexpokemon.presentationLayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.pokedexpokemon.dataLayer.di.injectFeature
import com.example.pokedexpokemon.dataLayer.utils.Ressource
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemon
import com.example.pokedexpokemon.domainLayer.usecase.GetPokemonList
import com.example.pokedexpokemon.presentationLayer.theme.PokedexPokemonTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class MainActivity : ComponentActivity() {
    val getList by inject<GetPokemonList>()
    val getPokemon by inject<GetPokemon>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        GlobalScope.launch {
            when(val resullt = getPokemon.invoke()){
                is Ressource.Error -> { println("result.data suicde ${resullt.error}")}
                is Ressource.Loading -> {}
                is Ressource.Success -> println("result.data suicde ${resullt.data}")
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
                        modifier = Modifier.padding(innerPadding)
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

@Preview
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(modifier: Modifier = Modifier) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(100) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Card (
                        onClick = {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                content = "Item $it"
                            )
                        },
                        colors = CardDefaults.cardColors(containerColor = Color.Yellow),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "", modifier = Modifier.size(55.dp))
                            Column(
                                modifier = Modifier.padding(start = 30.dp)
                            ) {
                                Text(text = "bulbisar", style = TextStyle( ))
                                Row(
                                    modifier = Modifier.fillMaxWidth(0.6f),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    AssistChip(onClick = { /*TODO*/ }, label = { Text(text = "plant")})
                                    AssistChip(onClick = { /*TODO*/ }, label = { Text(text = "plant")})

                                }

                            }
                        }

                    }
                  /*  Text(
                        "Item $it",
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .clickable {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail,
                                    content = "Item $it"
                                )
                            }
                            .padding(16.dp)
                    )*/
                }
            }
        },
        detailPane = {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an item"
            AnimatedPane {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "", modifier = Modifier.size(55.dp))
                    Text(text = content)
                    Row {
                        AssistChip(
                            onClick = {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Extra,
                                    content = "Option 1"
                                )
                            },
                            label = {
                                Text(text = "Option 1")
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        AssistChip(
                            onClick = {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Extra,
                                    content = "Option 2"
                                )
                            },
                            label = {
                                Text(text = "Option 2")
                            }
                        )
                    }
                }
            }
        },
        extraPane = {
            val content = navigator.currentDestination?.content?.toString() ?: "Select an option"
            AnimatedPane {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = content)
                }
            }
        }
    )
}

