package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malbolge.pokedex.R
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun DetailDataItem(
    modifier: Modifier = Modifier,
    value: Float,
    unit: String,
    icon: Painter
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = icon, contentDescription = null, tint = MaterialTheme.colors.onSurface)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "$value$unit", color = MaterialTheme.colors.onSurface)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme() {
        DetailDataItem(
            value = 10.0f,
            unit = "kg",
            icon = painterResource(id = R.drawable.baseline_balance_24)
        )
    }
}