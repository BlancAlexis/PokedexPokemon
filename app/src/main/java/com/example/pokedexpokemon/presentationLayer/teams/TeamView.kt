package com.example.pokedexpokemon.presentationLayer.teams

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexpokemon.R
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun TeamViewHost(
    viewModel: TeamViewModel = koinViewModel(), modifier: Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    TeamView(modifier, uiState.value, viewModel::onEvent)
}

@Composable
fun TeamView(
    modifier: Modifier, listUiState: List<DeckUiState>, onEvent: (TeamEvent) -> Unit

) {
    Column(
        modifier = modifier.then(
            Modifier.fillMaxSize()

        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Vos decks", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        LazyColumn(contentPadding = PaddingValues(vertical = 10.dp)) {
            itemsIndexed(listUiState) { index, deck ->
                DeckCard(deck)
            }
        }
    }
}


@Composable
private fun DeckCard(deck: DeckUiState) {
    var expandedState by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    SwipeToDeleteContainer(item = deck, onDelete = {

    }, animationDuration = 500) {
        Card(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    expandedState = !expandedState
                }
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300, easing = LinearOutSlowInEasing
                    )
                ),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Blue)) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = deck.name,
                        modifier = Modifier.padding(start = 10.dp),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(modifier = Modifier
                        .alpha(0.2f)
                        .rotate(rotationState), onClick = {
                        expandedState = !expandedState
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop-Down Arrow"
                        )
                    }
                }
                if (expandedState) {

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .background(Color.Magenta)
                            .fillMaxWidth()
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green),
                        ) {
                            itemsIndexed(100.downTo(0).toList()) { index, card ->
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("https://images.pokemontcg.io/ex9/4.png")
                                        .placeholder(R.drawable.ice).build(),
                                    contentDescription = "",
                                    modifier = Modifier.size(70.dp)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T, onDelete: (T) -> Unit, animationDuration: Int = 500, content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberSwipeToDismissBoxState(confirmValueChange = { value ->
        if (value == SwipeToDismissBoxValue.StartToEnd) {
            isRemoved = true
            true
        } else {
            false
        }
    })

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved, exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration), shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = state, backgroundContent = {
                DeleteBackground(swipeDismissState = state)
            }, enableDismissFromEndToStart = true, enableDismissFromStartToEnd = false
        ) {
            content(item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: SwipeToDismissBoxState
) {
    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White
        )
    }
}

