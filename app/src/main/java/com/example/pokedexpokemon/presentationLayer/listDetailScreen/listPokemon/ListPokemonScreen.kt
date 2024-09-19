package com.example.pokedexpokemon.presentationLayer.listDetailScreen.listPokemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.ListDetailsPokemonEvent
import com.example.pokedexpokemon.presentationLayer.util.SealedPokemonType

@Composable
fun ListPokemonScreen(
    modifier: Modifier = Modifier,
    uiState: List<ListDetailsPokemonUiState>,
    onNavigate: (Int) -> Unit = {},
    viewModelEvent: (ListDetailsPokemonEvent) -> Unit = {}
) {
    val context = LocalContext.current
    val itemAnticipation = 15
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp).fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
    ) {
        itemsIndexed(uiState, key = { _, item -> item.nationalIndices }) { index, value ->
            if ((index + itemAnticipation) >= uiState.size) {
                viewModelEvent(ListDetailsPokemonEvent.loadMore)
            }
            PokemonCard(onNavigate = {
                onNavigate(index)
            }, value, context)
        }
    }
}

@Composable
private fun PokemonCard(
    onNavigate: () -> Unit, pokemonUiState: ListDetailsPokemonUiState, context: Context
) {
    Card(onClick = {
        onNavigate()
    },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(16.dp),
                brush = Brush.linearGradient(colors = if (pokemonUiState.type.size > 1) {
                    pokemonUiState.type.map { it.color }
                } else {
                    listOf(pokemonUiState.type.first().color, pokemonUiState.type.first().color)
                })),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
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
                val imageLoader = ImageLoader.Builder(context).components {
                        add(ImageDecoderDecoder.Factory())
                    }.build()

                val painter: Painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context).data(pokemonUiState.sprites?.frontDefault).build(),
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
                        text = pokemonUiState.name.toString(),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "#" + pokemonUiState.nationalIndices.toString(),
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium)
                    )
                }
                PokemonTypeFlowRow(pokemonUiState.type)

            }

        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun PokemonTypeFlowRow(listPokemonType: List<SealedPokemonType>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        listPokemonType.forEach { type ->
            AssistChip(leadingIcon = {
                Icon(
                    painter = painterResource(id = type.icon),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = type.color,
                ),
                onClick = { /*TODO*/ },
                label = { Text(text = stringResource(id = type.name)) },
                modifier = Modifier.padding(horizontal = 5.dp)
            )
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
