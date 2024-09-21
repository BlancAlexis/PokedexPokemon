package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import Ability
import Move
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.pokedexpokemon.dataLayer.PokemonUiState
import com.example.pokedexpokemon.presentationLayer.NavigationEvent
import com.example.pokedexpokemon.presentationLayer.util.CustomDialog
import com.example.pokedexpokemon.presentationLayer.util.SealedPokemonType
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsPokemonScreen(
    modifier: Modifier = Modifier,
    uiState: PokemonUiState?,
    onNavigate: (String) -> Unit = {},
    navigationEvent: (NavigationEvent) -> Unit,
    playRoar: (String) -> Unit
) {
    val context = LocalContext.current
    var showSecret by rememberSaveable {
        mutableStateOf(false)
    }
    if (uiState == null) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
        ) {

        }
    }
    else {
        if (showSecret) {
            CustomDialog(
                onDismissRequest = { showSecret = false },
                onConfirmRequest = { showSecret = false },
                confirmButton = "OK",
                title = "Secret Ability",
                text = "Chlorophyll",
                icon = Icons.Filled.Star
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopSectionCard(uiState, context)
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
            BottomSectionCard(uiState, navigationEvent, playRoar, onNavigate)
        }
    }
}

@Composable
private fun BottomSectionCard(
    uiState: PokemonUiState,
    navigationEvent: (NavigationEvent) -> Unit,
    playRoar: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
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
        AbilitiesRow(uiState.abilities, navigationEvent)
        Button(
            onClick = { playRoar(uiState.roar.toString()) },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Rugissement")

        }
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.5f)
                .border(1.dp, Color.Black)
        )
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxHeight(0.7f)
        ) {
            Row(
                modifier = Modifier
                    .weight(6f)
                    .fillMaxHeight(),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 5.dp),
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
                    itemsIndexed(uiState.moves, key = { _, move -> move.moveName }) { index, move ->
                        AttackItem(move)
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
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

@Composable
private fun AbilitiesRow(
    uiState: List<Ability>?, navigaionEvent: (NavigationEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        uiState?.forEach {
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
                        navigaionEvent(NavigationEvent.Navigate("pokemonDialog"))
                    }) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "")

                    }
                }
            }
        }
    }
}

@Composable
private fun AttackItem(move: Move) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Text(
                text = "${move.moveName} lvl.${move.levelLearnedAt}", fontWeight = FontWeight.Medium
            )
        }
        HorizontalDivider(
            color = Color.Black, modifier = Modifier.fillMaxWidth(0.6f)
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun TopSectionCard(
    uiState: PokemonUiState, context: Context
) {
    Row(
        Modifier
            .shadow(10.dp, shape = RoundedCornerShape(1.dp, 1.dp, 120.dp, 120.dp))
            .background(brush = Brush.sweepGradient(colors = if (uiState.type.size > 1) {
                uiState.type.map { it.color }
            } else {
                listOf(uiState.type.first().color, uiState.type.first().color)
            }), shape = RoundedCornerShape(1.dp, 1.dp, 120.dp, 120.dp))
            .fillMaxHeight(0.3f)
            .border(
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(1.dp, 1.dp, 120.dp, 120.dp)
            )
            .clickable { }
            .padding(top = 40.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        val imageLoader = ImageLoader.Builder(context).components {
            add(ImageDecoderDecoder.Factory())

        }.build()
        val frontPainter = createPokemonPainter(uiState.sprites?.frontDefault, imageLoader, context)
        val backPainter = createPokemonPainter(uiState.sprites?.backDefault, imageLoader, context)
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
                            val pageOffset =
                                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                            alpha = lerp(
                                start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
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


            FormDataPokemon(uiState, modifier = Modifier.align(Alignment.TopStart))
        }
    }
}

@Composable
private fun FormDataPokemon(uiState: PokemonUiState, modifier: Modifier) {
    Column(
        modifier = modifier.then(
            Modifier
                .offset(x = (30).dp)
                .padding(2.dp)
        ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "taille", fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        Text(
            text = uiState.height.toString(), fontSize = 25.sp, fontWeight = FontWeight.Bold
        )
        Text(
            text = "poids", fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        Text(
            text = uiState.weight.toString(), fontSize = 25.sp, fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun createPokemonPainter(imageUrl: String?, imageLoader: ImageLoader, context: Context): Painter {
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(imageUrl).build(), imageLoader = imageLoader
    )
}

@Preview
@Composable
private fun previewDetailsPokemon() {
    DetailsPokemonScreen(uiState = PokemonUiState(
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
    ), navigationEvent = {}, playRoar = {})
}