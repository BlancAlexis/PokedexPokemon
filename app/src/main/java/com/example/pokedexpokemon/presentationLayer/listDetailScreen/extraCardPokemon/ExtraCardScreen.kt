package com.example.pokedexpokemon.presentationLayer.listDetailScreen.extraCardPokemon

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexpokemon.presentationLayer.DeckDialogEvent
import com.example.pokedexpokemon.presentationLayer.DeckDialogUiState
import com.example.pokedexpokemon.presentationLayer.DeckDialogViewModel
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
    ExtraCardScreen(uiState = pokemonCardPagingItems, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraCardScreen(
    uiState: LazyPagingItems<CardPokemonUiState>,
    onEvent: (CardPokemonEvent) -> Unit = {}
) {
    val context = LocalContext.current
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedCard by rememberSaveable { mutableStateOf<CardPokemonUiState?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
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
            DialogDeckHost()
            /* ModalBottomSheet(onDismissRequest = { isSheetOpen = false }) {
                selectedCard?.let { a ->
                    detailsSheet(a)
                }
            }*/
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun detailsSheet(uiState: CardPokemonUiState) {
    val context = LocalContext.current
    var a by rememberSaveable { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(context).data(uiState.image).build(),
            contentDescription = ""
        )
        Row(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = uiState.name, fontSize = 20.sp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { a = !a }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More"
                    )
                }

                DropdownMenu(
                    expanded = a,
                    onDismissRequest = { a = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Load") },
                        onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
                    )
                    DropdownMenuItem(
                        text = { Text("Save") },
                        onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
                    )

                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(0.7f), horizontalArrangement = Arrangement.SpaceAround
        ) {
            uiState.subtypes.forEachIndexed { i, type ->
                AssistChip(colors = AssistChipDefaults.assistChipColors(
                    containerColor = type.color
                ), onClick = { }, label = { Text(text = stringResource(id = type.name)) })
            }
        }
        Text(
            text = "artist : ${uiState.artist}", modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp)
        )
        Text(
            text = "rarity : ${uiState.rarity}", modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp)
        )
        Text(
            text = "number : ${uiState.number}", modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp)
        )
        Text(
            text = uiState.cardPrice.prices.high.toString(), modifier = Modifier
                .align(Alignment.End)
                .padding(start = 10.dp)
        )
        Text(
            text = uiState.cardPrice.prices.directLow.toString(), modifier = Modifier
                .align(Alignment.End)
                .padding(start = 10.dp)
        )
    }
}


@Composable
private fun DialogDeckHost(
    viewModel: DeckDialogViewModel = koinViewModel()
) {
    val uiState by viewModel.uistate.collectAsState()
    DeckDialog(uiState, viewModel::onEvent)

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun DeckDialog(uiState: DeckDialogUiState, onEvent: (DeckDialogEvent) -> Unit) {
    var re by rememberSaveable { mutableStateOf(false) }
    BasicAlertDialog(onDismissRequest = { /*TODO*/ }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp)
        ) {
            LazyColumn {
                uiState.deckAvailable?.let { it ->
                    itemsIndexed(it) { index, value ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = "${value.name}")
                            Checkbox(
                                checked = value.isSelect,
                                onCheckedChange = { onEvent(DeckDialogEvent.TriggerSelectedDeck(index)) })
                        }
                    }
                }

            }
                androidx.compose.animation.AnimatedVisibility(visible = re) {
                    BasicTextField2(state = uiState.newDeck, modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black).padding(horizontal = 10.dp))

            }
            Button(onClick = {re = !re }) {
                Text(text = "Create")
            }
        }

    }
}