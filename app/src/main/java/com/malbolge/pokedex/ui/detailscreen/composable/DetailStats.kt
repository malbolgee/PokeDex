package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun DetailStats(
    name: String,
    value: Int,
    maxValue: Int,
    color: Color,
    animationDelay: Int = 100
) {

    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            value / maxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            1000,
            animationDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
            .clip(CircleShape)
            .background(
                if (isSystemInDarkTheme()) Color(0xFF505050) else Color.LightGray
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curPercent.value)
                .clip(CircleShape)
                .background(color)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(
                text = "${(curPercent.value * maxValue).toInt()}",
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        DetailStats("HP", 80, 100, Color.Yellow)
    }
}