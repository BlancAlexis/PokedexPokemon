package com.example.pokedexpokemon.presentationLayer.teams.deckDialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun DialogDeckHost(
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
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 10.dp)
        ) {
            LazyColumn {
                uiState.deckAvailable?.let { it ->
                    itemsIndexed(it) { index, value ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = value.name, modifier = Modifier.padding(start = 10.dp))
                            Checkbox(
                                checked = value.isSelect,
                                onCheckedChange = {
                                    onEvent(
                                        DeckDialogEvent.TriggerSelectedDeck(
                                            index
                                        )
                                    )
                                })
                        }
                    }
                }

            }
            androidx.compose.animation.AnimatedVisibility(
                visible = re,
                enter = androidx.compose.animation.fadeIn(),
                exit = androidx.compose.animation.fadeOut()
            ) {
                BasicTextField2(
                    state = uiState.newDeck,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(horizontal = 10.dp)
                )

            }
            Button(onClick = { re = !re }) {
                Text(text = "Ajouter")
            }
            Button(
                onClick = { onEvent(DeckDialogEvent.SaveDeck) },
                shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                Text(text = "Confirmer")
            }
        }

    }
}