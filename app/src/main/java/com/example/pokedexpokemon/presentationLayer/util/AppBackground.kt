package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AppBackground() {
    Column {


        Row(
            modifier = Modifier
                .padding(top = 40.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {
            Canvas(modifier = Modifier.size(85.dp).offset(y = (10.dp))) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawCircle(
                    color = Color.Gray,
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = 80f
                )

                drawCircle(
                    color = Color.Blue,
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = 70f
                )
            }

            Row(
                modifier = Modifier.offset(x = -(20.dp))
            ) {
                Canvas(modifier = Modifier.size(50.dp)) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawCircle(
                        color = Color.Black,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 32f
                    )

                    drawCircle(
                        color = Color.Red,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 25f
                    )
                }

                Canvas(modifier = Modifier.size(50.dp)) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawCircle(
                        color = Color.Black,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 32f
                    )

                    drawCircle(
                        color = Color.Yellow,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 25f
                    )
                }
                Canvas(modifier = Modifier.size(50.dp)) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawCircle(
                        color = Color.Black,
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 32f
                    )

                    drawCircle(
                        color = Color(0xff0f9d58),
                        center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                        radius = 25f
                    )
                }
            }
        }


}
}



@Preview
@Composable
private fun d() {
    LazyRow(modifier = Modifier
        .offset(x = -30f.dp,)
        .requiredWidth(900.dp)
        .rotate(30f)
        .border(1.dp, Color.Red)
    ){
        items(1000){
            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(50.dp))

        }
    }
    LazyRow(modifier = Modifier
        .offset(x = -30f.dp,)
        .requiredWidth(900.dp)
        .rotate(30f)
        .border(1.dp, Color.Red)
    ){
        items(1000){
            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(50.dp))

        }
    }
    LazyRow(modifier = Modifier
        .offset(x = -30f.dp,)
        .requiredWidth(900.dp)
        .rotate(30f)
        .border(1.dp, Color.Red)
    ){
        items(1000){
            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(50.dp))

        }
    }
    Icon(imageVector = Icons.Default.Home, contentDescription = "", modifier = Modifier.size(200.dp))
    LazyRow(modifier = Modifier
        .offset(x = -30f.dp,)
        .requiredWidth(900.dp)
        .rotate(30f)
        .border(1.dp, Color.Red)
    ){
        items(1000){
            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(50.dp))

        }
    }
    LazyRow(modifier = Modifier
        .offset(x = -30f.dp,)
        .requiredWidth(900.dp)
        .rotate(30f)
        .border(1.dp, Color.Red)
    ){
        items(1000){
            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(50.dp))

        }
    }
    LazyRow(modifier = Modifier
        .offset(x = -30f.dp,)
        .requiredWidth(900.dp)
        .rotate(30f)
        .border(1.dp, Color.Red)
    ){
        items(1000){
            Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.size(50.dp))

        }
    }
}