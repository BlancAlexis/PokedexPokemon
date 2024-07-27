package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import android.widget.ImageView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.PokedexProgressBar
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsPokemonScreen(uiState: ListDetailsPokemonUiState, onNavigate: () -> Unit = {}) {
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
                .wrapContentSize()
                .background(Color.Cyan)
                .size(200.dp)
                .padding(top = 40.dp, bottom = 20.dp), horizontalArrangement = Arrangement.Center
        ) {
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    add(ImageDecoderDecoder.Factory())

                }
                .build()

            val painter: Painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(uiState.sprites?.frontDefault)
                    .build(),
                imageLoader = imageLoader
            )
            val painter2: Painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(uiState.sprites?.backDefault)
                    .build(),
                imageLoader = imageLoader
            )
            val a =listOf(painter,painter2)
            val pagerState = rememberPagerState(pageCount = { a.size })
            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                }
            }
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
                        painter = a[page],
                        contentDescription = ""
                    )
                    Text(
                        text = "uiState.listMaintenance[page].name",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 25.dp, top = 200.dp)
                    )
                }
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(bottom = 20.dp, top = 5.dp, start = 3.dp, end = 3.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(6.dp)
                    )
                }
            }
        }


        Text(text = uiState.name.toString(), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(0.7f), horizontalArrangement = Arrangement.SpaceAround
        ) {
            uiState.type.forEachIndexed { i, type ->
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
                    onClick = { onNavigate() },
                    label = { Text(text = stringResource(id = type.name)) })
            }
        }
        Row {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Gray),
                shape = RoundedCornerShape(5.dp,0.dp ,0.dp,5.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
            ) {
                    Box (
                        Modifier.fillMaxWidth(0.2f)
                    ){
                        Column {
                           // Text(text = "poids", fontSize = 14.sp, modifier = Modifier.align(Alignment.TopCenter))
                            HorizontalDivider(modifier = Modifier
                                .wrapContentWidth()
                                .height(1.dp), color = Color.Blue)
                        }

                        Spacer(modifier = Modifier.fillMaxHeight(0.08f))
                        Text(text = uiState.height.toString(), fontSize = 25.sp, modifier = Modifier.align(Alignment.BottomCenter))
                    }

            }
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                shape = RoundedCornerShape(0.dp, 5.dp,5.dp,0.dp)
            ) {
                Text(text = uiState.weight.toString())

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Color.Blue)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .background(Color.Red)
            ) {

            }
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .background(Color.Green)
            ) {
LazyColumn {
    itemsIndexed(uiState.moves){ index , move->
        Row {
            Text(text = move.moveName)
            Text(text = "lvl.${move.levelLearnedAt}")
        }
    }
}
            }
        }
    }
}

@Preview
@Composable
private fun previewDetailsPokemon() {
    // DetailsPokemonScreen(uiState)
}