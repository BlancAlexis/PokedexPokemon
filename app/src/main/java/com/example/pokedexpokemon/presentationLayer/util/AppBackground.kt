package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexpokemon.R

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
            Canvas(
                modifier = Modifier
                    .size(85.dp)
                    .offset(y = (10.dp))
            ) {
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun d() {

    val array = listOf(
        R.drawable.beacon_badge,
        R.drawable.balance_badge,
        R.drawable.boulder_badge,
        R.drawable.balance_badge,
        R.drawable.fen_badge,
        R.drawable.fog_badge,
        R.drawable.cascade_badge,
        R.drawable.coal_badge,
        R.drawable.dynamo_badge,
        R.drawable.earth_badge,
        R.drawable.feather_badge,
        R.drawable.forest_badge,
        R.drawable.glacier_badge,
        R.drawable.heat_badge,
        R.drawable.hive_badge,
        R.drawable.knuckle_badge,
        R.drawable.marsh_badge,
        R.drawable.icicle_badge,
        R.drawable.mind_badge, R.drawable.mine_badge,
        R.drawable.plain_badge,
        R.drawable.rain_badge,
        R.drawable.relic_badge,
        R.drawable.rising_badge,
        R.drawable.rainbow_badge,
        R.drawable.soul_badge,
        R.drawable.stone_badge,
        R.drawable.storm_badge,
        R.drawable.thunder_badge,
        R.drawable.volcano_badge,
        R.drawable.fairy,
    )
    val width = LocalConfiguration.current.screenWidthDp.dp
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        AutoScrollingLazyRow(
            list = array,
            Modifier
                .requiredWidth(width * 1.25f)
                .rotate(30f)
                
        ) {
            Image(
                painter = painterResource(id = array.random()),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AutoScrollingLazyRow(
            list = array,
            Modifier
                .requiredWidth(width * 1.25f)
                .rotate(30f)
                
        ) {
            Image(
                painter = painterResource(id = array.random()),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        AutoScrollingLazyRow(
            list = array,
            Modifier
                .requiredWidth(width * 1.25f)
                .rotate(30f)
                
        ) {
            Image(
                painter = painterResource(id = array.random()),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.poke_ball_icon),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        AutoScrollingLazyRow(
            list = array,
            Modifier
                .requiredWidth(width * 1.25f)
                .rotate(30f)
                
        ) {
            Image(
                painter = painterResource(id = array.random()),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        AutoScrollingLazyRow(
            list = array,
            Modifier
                .requiredWidth(width * 1.25f)
                .rotate(30f)
                
        ) {
            Image(
                painter = painterResource(id = array.random()),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        AutoScrollingLazyRow(
            list = array,
            Modifier
                .requiredWidth(width * 1.25f)
                .rotate(30f)
                
        ) {
            Image(
                painter = painterResource(id = array.random()),
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(50.dp)
            )
        }
    }
}

@Composable
fun LazyListItem(text: String) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(150.dp)
            .background(
                color = Color.White, shape = RoundedCornerShape(8.dp)
            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, fontSize = 24.sp
        )
    }
}