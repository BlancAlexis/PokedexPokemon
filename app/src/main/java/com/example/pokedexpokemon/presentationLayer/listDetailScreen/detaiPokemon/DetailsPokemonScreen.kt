package com.example.pokedexpokemon.presentationLayer.listDetailScreen.detaiPokemon

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.pokedexpokemon.dataLayer.ListDetailsPokemonUiState
import com.example.pokedexpokemon.presentationLayer.PokedexProgressBar

@Composable
fun DetailsPokemonScreen(uiState: ListDetailsPokemonUiState?= null, onNavigate: () -> Unit = {}) {
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
                .padding(top = 40.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val view = remember { ImageView(context) }
            DisposableEffect(context) {
                Glide.with(context).asGif().load(uiState?.sprites?.frontDefault).into(view)
                onDispose {
                    Glide.with(context).clear(view)
                }
            }
            AndroidView(factory = { view })
        }
        Text(text = uiState?.name.toString(), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Row (
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            uiState?.type?.forEachIndexed { i, type ->
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
                    onClick = { /*TODO*/ },
                    label = { Text(text = stringResource(id = type.name)) })
            }
        }
        Row {
            Row(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                LinearProgressIndicator(
                    progress = { 0.5f },
                    color = Color.Yellow,
                    strokeCap = StrokeCap.Round
                )
                LinearProgressIndicator(
                    progress = { 0.5f },
                    color = Color.Yellow
                )
                LinearProgressIndicator(
                    progress = { 0.5f },
                    color = Color.Yellow
                )
                LinearProgressIndicator(
                    progress = { 0.5f },
                    color = Color.Yellow
                )
                LinearProgressIndicator(
                    progress = { 0.5f },
                    color = Color.Yellow
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .background(Color.Red)
            ) {
                Text(text = "e")

            }

        }
    }
}

@Preview
@Composable
private fun previewDetailsPokemon() {
   // DetailsPokemonScreen(uiState)
}