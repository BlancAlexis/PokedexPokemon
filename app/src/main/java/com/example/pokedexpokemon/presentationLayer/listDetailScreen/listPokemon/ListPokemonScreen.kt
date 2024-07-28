package com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailPokemonViewModel
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsPokemonEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListPokemonScreen(
    uiState: LazyPagingItems<ListDetailsPokemonUiState>,
    onNavigate: (Int) -> Unit = {},
    viewModelEvent: (ListDetailsPokemonEvent) -> Unit = {}
) {
    val context = LocalContext.current
/*    val viewModel = koinViewModel<ListDetailPokemonViewModel>()
    val state : LazyPagingItems<ListDetailsPokemonUiState> =viewModel.uiState.collectAsLazyPagingItems()*/
    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(uiState.itemSnapshotList, key = { _, item -> item!!.nationalIndices }) { index, value ->
            Spacer(modifier = Modifier.height(20.dp))
            if (value != null) {
                Card(
                    onClick = {
                        //viewModelEvent(ListDetailsPokemonEvent.playRoar(value.roar!!))
                        onNavigate(index)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            brush = Brush.linearGradient(
                                colors = if (value.type.size > 1) {
                                    value.type.map { it.color }
                                } else {
                                    listOf(value.type.first().color, value.type.first().color)
                                }
                            )
                        ),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
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
                                      val imageLoader = ImageLoader.Builder(context)
                                          .components {
                                              add(ImageDecoderDecoder.Factory())

                                          }
                                          .memoryCache(MemoryCache.Builder(context).maxSizePercent(1.0).build())
                                          .build()

                                      val painter: Painter = rememberAsyncImagePainter(
                                          model = ImageRequest.Builder(context)
                                              .data(value.sprites?.frontDefault)
                                              .build(),
                                          imageLoader = imageLoader
                                      )
                                      Image(
                                          painter = painter,
                                          contentDescription = null,
                                          modifier = Modifier.fillMaxSize(),
                                          alignment = Alignment.Center
                                      )
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
                                horizontalArrangement = Arrangement.Start
                            ) {
                                value.type.forEachIndexed { i, type ->
                                    AssistChip(
                                        leadingIcon = {
                                            Icon(
                                                painter = painterResource(id = type.icon),
                                                tint = Color.Black,
                                                contentDescription = "",
                                                modifier = Modifier.size(25.dp)
                                            )
                                        },
                                        modifier = Modifier.padding(end = 15.dp),
                                        colors = ChipColors(
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
