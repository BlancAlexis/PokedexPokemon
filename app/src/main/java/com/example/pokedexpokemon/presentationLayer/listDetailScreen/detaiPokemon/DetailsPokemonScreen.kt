package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.pokedexpokemon.presentationLayer.PokedexProgressBar
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsPokemonUiState

@Composable
fun DetailsPokemonScreen(uiState: ListDetailsPokemonUiState?= null, onNavigate: () -> Unit = {}) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxSize(0.3f)
                .background(Color.Cyan)
                .size(55.dp)
        ) {
            val view = remember { ImageView(context) }
            DisposableEffect(context) {
                Glide.with(context).asGif().load("uiState.sprite").into(view)
                onDispose {
                    Glide.with(context).clear(view)
                }
            }
            AndroidView(factory = { view })
        }
        Text(text = "Salamèche")
        Row {
            AssistChip(onClick = {
//                navigator.navigateTo(
//                    pane = ListDetailPaneScaffoldRole.Extra, content = "Option 1"
//                )
            }, label = {
                Text(text = "Option 1")
            })
            Spacer(modifier = Modifier.width(16.dp))
            AssistChip(onClick = {
//                navigator.navigateTo(
//                    pane = ListDetailPaneScaffoldRole.Extra, content = "Option 2"
//                )
            }, label = {
                Text(text = "Option 2")
            })
        }
        Row {
            Row(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .widthIn(min = 20.dp),
                        text = "pokedexStatus.type",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                    PokedexProgressBar(
                        color = Color.Yellow, progress = 0.1f, label = "As"
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .widthIn(min = 20.dp),
                        text = "pokedexStatus.type",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                    PokedexProgressBar(
                        color = Color.Yellow, progress = 0.1f, label = "As"
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .widthIn(min = 20.dp),
                        text = "pokedexStatus.type",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                    PokedexProgressBar(
                        color = Color.Yellow, progress = 0.1f, label = "As"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .widthIn(min = 20.dp),
                        text = "pokedexStatus.type",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                    PokedexProgressBar(
                        color = Color.Yellow, progress = 0.1f, label = "As"
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .widthIn(min = 20.dp),
                        text = "pokedexStatus.type",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                    PokedexProgressBar(
                        color = Color.Yellow, progress = 0.1f, label = "As"
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .background(Color.Red)
            ) {
                Text(text = "e")

            }

        }
    }
}

@Preview
@Composable
private fun previewDetailsPokemon() {
   // DetailsPokemonScreen(uiState)
}