package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.CardPokemonUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExtraCardHost(
    viewModel: CardPokemonViewModel = koinViewModel(), name: String
) {
    viewModel.getPokemonByName(name)
    val pokemonCardPagingItems: LazyPagingItems<CardPokemonUiState> =
        viewModel.uiState.collectAsLazyPagingItems()
    if (pokemonCardPagingItems.itemCount == 0) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
    ExtraCardScreen(uiState = pokemonCardPagingItems)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraCardScreen(uiState: LazyPagingItems<CardPokemonUiState>) {
    val context = LocalContext.current
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedCard by rememberSaveable { mutableStateOf<CardPokemonUiState?>(null) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(uiState.itemCount) { index ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .wrapContentSize()
                        .clickable {
                            isSheetOpen = true
                            selectedCard = uiState[index]
                        }, elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                ) {
                    val a = ImageRequest.Builder(context).data(uiState[index]?.image).build()
                    AsyncImage(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        model = a,
                        contentDescription = ""
                    )
                }
            }
        }
        if (isSheetOpen) {
            ModalBottomSheet(onDismissRequest = { isSheetOpen = false }) {
                selectedCard?.let { a ->
                    detailsSheet(a)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun detailsSheet(uiState: CardPokemonUiState) {
    val context = LocalContext.current
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        AsyncImage(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(context).data(uiState.image).build(),
            contentDescription = ""
        )
        Text(text = uiState.name, fontSize = 20.sp)
        Row(
            modifier = Modifier.fillMaxWidth(0.7f), horizontalArrangement = Arrangement.SpaceAround
        ) {
            uiState.subtypes.forEachIndexed { i, type ->
                AssistChip(colors = AssistChipDefaults.assistChipColors(
                    containerColor = type.color
                ), onClick = { }, label = { Text(text = stringResource(id = type.name)) })
            }
        }
        Text(text = uiState.artist, modifier = Modifier.align(Alignment.End))
        Text(text = uiState.rarity, modifier = Modifier.align(Alignment.End))
        Text(text = uiState.number, modifier = Modifier.align(Alignment.End))
        Text(text = uiState.cardPrice.prices.high.toString(), modifier = Modifier.align(Alignment.End))
        Text(text = uiState.cardPrice.prices.directLow.toString(), modifier = Modifier.align(Alignment.End))
    }
}