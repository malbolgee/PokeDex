package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun DetailCard(modifier: Modifier = Modifier, lazySection: @Composable () -> Unit = {}) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        elevation = 4.dp
    ) {
        lazySection()
    }
}