package com.example.brawlinfo.presentation.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brawlinfo.R
import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.ui.theme.AppFontFamily
import com.example.brawlinfo.ui.theme.ErrorRed
import com.example.brawlinfo.ui.theme.ErrorRedDark
import com.example.brawlinfo.ui.theme.Primary

@Composable
fun LoadingProgressBar() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(1500, easing = FastOutSlowInEasing)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (isSystemInDarkTheme()) {
                    Color.Black
                } else Color.DarkGray
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
                .rotate(angle),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Loading",
            fontFamily = AppFontFamily,
            color = Primary
        )
    }
}

@Composable
fun ErrorMessage(
    listState: MutableState<List<Brawler>?>? = null,
    idState: MutableState<Brawler?>? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (isSystemInDarkTheme()) {
                    Color.Black
                } else Color.DarkGray
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = null
        )
        val color = if (isSystemInDarkTheme()) {
            ErrorRedDark
        } else ErrorRed
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Error with downloading of the data. Please, try again",
            fontFamily = AppFontFamily,
            color = if (isSystemInDarkTheme()) {
                ErrorRedDark
            } else ErrorRed
        )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            onClick = {
                listState?.value = null
                idState?.value = null
            },
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color)
            ) {
                Text(
                    text = "Try again",
                    color = Color.White,
                    fontFamily = AppFontFamily,
                )
            }
        }
    }
}