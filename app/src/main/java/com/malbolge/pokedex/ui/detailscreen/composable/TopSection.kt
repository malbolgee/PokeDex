package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun TopSection(modifier: Modifier = Modifier, onBackNavigation: () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .offset(15.dp, 16.dp)
                .clickable {
                    onBackNavigation()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        TopSection()
    }
}