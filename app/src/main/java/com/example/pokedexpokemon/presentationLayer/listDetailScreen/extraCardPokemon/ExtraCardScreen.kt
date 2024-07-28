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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon.CardPokemonUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExtraCardHost(
    viewModel: CardPokemonViewModel = koinViewModel(),
    name: String
) {
    viewModel.getPokemonByName(name)
    val pokemonCardPagingItems: LazyPagingItems<CardPokemonUiState> = viewModel.uiState.collectAsLazyPagingItems()
    if(pokemonCardPagingItems.itemCount == 0){
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
    var isSheetOpen by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(uiState.itemCount) { index ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .wrapContentSize()
                        .clickable { isSheetOpen = true },
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        model = ImageRequest.Builder(context)
                            .data(uiState[index]?.image)
                            .build(), contentDescription = ""
                    )

                    println("image")
                }
            }
        }
        if (isSheetOpen) {
            ModalBottomSheet(onDismissRequest = { isSheetOpen = false }) {
                Column {
                    Image(

                    )
                    Text(text = "titre")
                    Row(
                        modifier = Modifier.fillMaxWidth(0.7f),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        uiState.itemSnapshotList.items.forEachIndexed { i, type ->
                            AssistChip(colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color.Blue
                            ),
                                onClick = { onNavigate(uiState.name?.lowercase() ?: "") },
                                label = { Text(text = stringResource(id = type.name)) })
                        }
                    }
                    Text(text = "auteur", modifier = Modifier.align(Alignment.End))
                }

            }
        }

    }
}

