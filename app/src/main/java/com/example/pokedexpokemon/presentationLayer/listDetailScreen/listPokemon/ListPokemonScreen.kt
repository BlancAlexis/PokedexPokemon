package com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsPokemonUiState

@Composable
fun ListPokemonScreen(
 uiState: List<ListDetailsPokemonUiState>,
 onNavigate : () -> Unit = {}
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(uiState,  key = { _, item -> item.id }) { index, value ->
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                onClick = {
                    onNavigate()
                },
                colors = CardDefaults.cardColors(containerColor = Color.Yellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .background(Color.Cyan)
                            .size(55.dp)
                    ) {
                        val view = remember { ImageView(context) }
                        DisposableEffect(context) {
                            Glide.with(context)
                                .asGif()
                                .load(value.sprites?.frontShiny)
                                .into(view)
                            onDispose {
                                Glide.with(context).clear(view)
                            }
                        }
                        AndroidView(factory = { view })
                    }
                    Column(
                        modifier = Modifier.padding(start = 15.dp)
                    ) {
                        Text(text = "bulbisar", style = TextStyle())
                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            AssistChip(
                                onClick = { /*TODO*/ },
                                label = { Text(text = "plant") })
                            AssistChip(
                                onClick = { /*TODO*/ },
                                label = { Text(text = "plant") })

                        }

                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun previewListPokemonScreen() {
    ListPokemonScreen(uiState =
    listOf(
        ListDetailsPokemonUiState(
        id = 1,
        height = 23,
        baseExperience = 4,
        name = "Salamèche"
    )
    )
    )
}