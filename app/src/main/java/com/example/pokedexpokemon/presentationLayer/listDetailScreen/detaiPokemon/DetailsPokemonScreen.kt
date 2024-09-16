package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import Ability
import Sprites
import Stat
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.NavigationEvent
import com.example.pokedexpokemon.presentationLayer.util.SealedPokemonType
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsPokemonScreen(
    modifier: Modifier = Modifier,
    uiState: ListDetailsPokemonUiState?,
    onNavigate: (String) -> Unit = {},
    navigaionEvent: (NavigationEvent) -> Unit
) {
    val context = LocalContext.current
    if (uiState == null) {
        CircularProgressIndicator()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                Modifier
                    .shadow(10.dp, shape = RoundedCornerShape(1.dp, 1.dp, 120.dp, 120.dp))
                    .background(brush = Brush.sweepGradient(
                        colors = if (uiState.type.size > 1) {
                            uiState.type.map { it.color }
                        } else {
                            listOf(uiState.type.first().color, uiState.type.first().color)
                        }
                    ), shape = RoundedCornerShape(1.dp, 1.dp, 120.dp, 120.dp))
                    .fillMaxHeight(0.3f)
                    .border(
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(1.dp, 1.dp, 120.dp, 120.dp)
                    )
                    .clickable { }
                    .padding(top = 40.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        add(ImageDecoderDecoder.Factory())

                    }
                    .build()
                val frontPainter =
                    createPokemonPainter(uiState.sprites?.frontDefault, imageLoader, context)
                val backPainter =
                    createPokemonPainter(uiState.sprites?.backDefault, imageLoader, context)
                val painters = listOf(frontPainter, backPainter)
                val pagerState = rememberPagerState(pageCount = { painters.size })
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                    }
                }
                Box() {
                    HorizontalPager(state = pagerState) { page ->
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .graphicsLayer {
                                    val pageOffset = (
                                            (pagerState.currentPage - page) + pagerState
                                                .currentPageOffsetFraction
                                            ).absoluteValue

                                    alpha = lerp(
                                        start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                                },
                        ) {

                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painters[page],
                                contentDescription = ""
                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            .offset(x = (30).dp)
                            .padding(2.dp)
                            .align(Alignment.TopStart),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "taille",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = uiState.height.toString(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "poids",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = uiState.weight.toString(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }
            Text(text = uiState.name.toString(), fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                uiState.type.forEachIndexed { i, type ->
                    AssistChip(colors = AssistChipDefaults.assistChipColors(
                        containerColor = type.color
                    ),
                        onClick = { onNavigate(uiState.name?.lowercase() ?: "") },
                        label = { Text(text = stringResource(id = type.name)) })
                }
            }
            Card(
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Gray),
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                    .fillMaxSize()
                    .border(
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
                    )
            ) {
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                        .background(Color.Black)
                )
                Text(
                    text = "Abilities",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    uiState.abilities?.forEach {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = it.abilityName.capitalize(Locale.current),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (it.isHidden) {
                                IconButton(onClick = {
                                    navigaionEvent(NavigationEvent.Navigate("deckDialog"))
                                }) {
                                    Icon(imageVector = Icons.Filled.Star, contentDescription = "")

                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.5f)
                        .border(1.dp, Color.Black)
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxHeight(0.6f)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(6f)
                            .fillMaxHeight(),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 5.dp),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.Start

                        ) {
                            Text(
                                text = "Statistique",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            uiState.stats.forEach {
                                Text(text = it.name)
                                LinearProgressIndicator(
                                    progress = { it.baseStat / 100f },
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                    Row(modifier = Modifier.weight(4f)) {
                        LazyColumn(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.DarkGray, RoundedCornerShape(10.dp)),
                            contentPadding = PaddingValues(10.dp)

                        ) {
                            item {
                                Text(text = "Attaques")
                            }
                            itemsIndexed(
                                uiState.moves,
                                key = { _, move -> move.moveName }) { index, move ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                    ) {
                                        Text(
                                            text = "${move.moveName} lvl.${move.levelLearnedAt}",
                                            fontWeight = FontWeight.Medium
                                        )
                                        Text(text = "")
                                    }
                                    HorizontalDivider(
                                        color = Color.Black,
                                        modifier = Modifier.fillMaxWidth(0.6f)
                                    )
                                }
                            }
                        }
                    }
                }

                uiState.gameIndices?.size?.let {
                    LazyRow(modifier = Modifier.background(Color.Cyan)) {
                        items(it) { index ->
                            Column {
                                Text(text = uiState.gameIndices[index].version.name)
                                Text(text = uiState.gameIndices[index].gameIndex.toString())
                            }

                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onNavigate(uiState.name?.lowercase() ?: "") },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Carte")
                    }
                }
            }
        }
    }
}

@Composable
fun createPokemonPainter(imageUrl: String?, imageLoader: ImageLoader, context: Context): Painter {
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .build(),
        imageLoader = imageLoader
    )
}

@Preview
@Composable
private fun previewDetailsPokemon() {
    DetailsPokemonScreen(
        uiState = ListDetailsPokemonUiState(
            name = "Bulbasaur",
            type = listOf(SealedPokemonType.POISON(), SealedPokemonType.GRASS()),
            abilities = listOf(Ability("Chlorophyll", false), Ability("Chlorophyll", false)),
            moves = listOf(),
            height = 7,
            weight = 69,
            sprites = Sprites(
                baseSprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                backDefault = "",
                frontDefault = ""
            ),
            stats = listOf(
                Stat("hp", 45, 2)
            ),
            nationalIndices = 1
        ),
        navigaionEvent = {}
    )
}