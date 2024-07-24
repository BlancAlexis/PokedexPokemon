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
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.pokedexpokemon.dataLayer.ListDetailsState

@Composable
fun ListPokemonScreen(
    uiState: ListDetailsState.onFirstSalveLoad, onNavigate: (Int) -> Unit = {}
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(uiState.uiStates, key = { _, item -> item.nationalIndices }) { index, value ->
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                onClick = {
                    onNavigate(index)
                },
                colors = CardDefaults.cardColors(containerColor = Color.Yellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .size(55.dp)
                    ) {
//                        val view = remember { ImageView(context) }
//                        DisposableEffect(context) {
//                            Glide.with(context).asGif()
//                                .load(value.sprites?.frontDefault ?: value.sprites?.backDefault)
//                                .into(view)
//                            onDispose {
//                                Glide.with(context).clear(view)
//                            }
//                        }
//                        AndroidView(factory = { view })
                    }
                    Column(
                        modifier = Modifier.padding(start = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = value.name.toString(),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "#" + value.nationalIndices.toString(),
                                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            value.type.forEachIndexed { i, type ->
                                AssistChip(colors = ChipColors(
                                    containerColor = type.color,
                                    labelColor = Color.Black,
                                    leadingIconContentColor = Color.Black,
                                    disabledLabelColor = Color.Black,
                                    disabledContainerColor = Color.Black,
                                    trailingIconContentColor = Color.Black,
                                    disabledLeadingIconContentColor = Color.Black,
                                    disabledTrailingIconContentColor = Color.Black
                                ),
                                    onClick = { /*TODO*/ },
                                    label = { Text(text = stringResource(id = type.name)) })
                            }
                        }

                    }

                }
            }
        }
    }
}

/*
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
}*/
