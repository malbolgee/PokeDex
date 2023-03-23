package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malbolge.pokedex.R
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import kotlin.math.round

@Composable
fun DetailDataSection(weight: Int, height: Int) {
    val kgWeight = remember { round(weight / 10f) }
    val meterHeight = remember { round(height / 10f) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DetailDataItem(
            modifier = Modifier.weight(1f),
            value = kgWeight,
            unit = "kg",
            icon = painterResource(id = R.drawable.baseline_balance_24)
        )
        Spacer(
            modifier = Modifier
                .size(1.dp, 80.dp)
                .background(Color.LightGray)
        )
        DetailDataItem(
            modifier = Modifier.weight(1f),
            value = meterHeight,
            unit = "m",
            icon = painterResource(id = R.drawable.baseline_height_24)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme() {
        DetailDataSection(weight = 65, height = 65)
    }

}