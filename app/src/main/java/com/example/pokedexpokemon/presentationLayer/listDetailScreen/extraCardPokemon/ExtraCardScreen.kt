package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexpokemon.R
import com.example.pokedexpokemon.presentationLayer.NavigationEvent
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.CardPokemonUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExtraCardHost(
    name: String,
    navigationEvent: (NavigationEvent) -> Unit = {},
    viewModel: CardPokemonViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = name) {
        viewModel.getPaginateCards(name)
    }
    val pokemonCardPagingItems: LazyPagingItems<CardPokemonUiState> =
        viewModel.uiState.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        if ((pokemonCardPagingItems.loadState.refresh is LoadState.Loading) || (pokemonCardPagingItems.loadState.append is LoadState.Loading)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        ExtraCardScreen(uiState = pokemonCardPagingItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraCardScreen(uiState: LazyPagingItems<CardPokemonUiState>) {
    val context = LocalContext.current
    var isSheetOpenIndex by rememberSaveable {
        mutableIntStateOf(-1)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(

            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(uiState.itemCount, key = uiState.itemKey()) { index ->
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 10.dp)
                        .clickable { isSheetOpenIndex = index },
                    model = ImageRequest.Builder(context)
                        .data(uiState[index]?.image)
                        .build(),
                    placeholder = painterResource(id = R.drawable.back_card_pokemon),
                    contentDescription = "",
                )

            }
        }
    }
    if (isSheetOpenIndex != -1) {
        ModalBottomSheet(onDismissRequest = { isSheetOpenIndex = -1 }) {
            DetailsCardSheet(uiState = uiState.itemSnapshotList.items[isSheetOpenIndex])

        }
    }
}

@Composable
private fun DetailsCardSheet(
    uiState: CardPokemonUiState, navigationEvent: (NavigationEvent) -> Unit = {}
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(300.dp),
            model = ImageRequest.Builder(context).data(uiState.image).build(),
            contentDescription = ""
        )
        Row(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = uiState.name, fontSize = 20.sp)
            DropDownBox(context, navigationEvent)
        }

        CardDetails(uiState)
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        PriceBar(uiState)

        CardDetails(uiState)
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        PriceBar(uiState)

    }
}

@Composable
private fun DropDownBox(
    context: Context, navigationEvent: (NavigationEvent) -> Unit
) {
    var dropDownStatus by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { dropDownStatus = !dropDownStatus }) {
            Icon(
                imageVector = Icons.Default.MoreVert, contentDescription = "More"
            )
        }

        DropdownMenu(expanded = dropDownStatus, onDismissRequest = { dropDownStatus = false }) {
            DropdownMenuItem(text = { Text("Load") },
                onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() })
            DropdownMenuItem(text = { Text("Save") },
                onClick = { navigationEvent(NavigationEvent.Navigate("deckDialog")) })

        }
    }
}

@Composable
private fun CardDetails(uiState: CardPokemonUiState) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
    ) {
        Text(
            text = "artist : ${uiState.artist}",

            )
        Text(
            text = "rarity : ${uiState.rarity}",
        )
        Text(
            text = "number : ${uiState.number}",
        )
    }
}

@Composable
private fun PriceBar(uiState: CardPokemonUiState) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .border(1.dp, Color.Black)
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color.Green, Color.Yellow, Color.Red)
                )
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = uiState.cardPrice.prices.directLow.toString() + "€",
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = uiState.cardPrice.prices.high.toString() + "€",
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}
