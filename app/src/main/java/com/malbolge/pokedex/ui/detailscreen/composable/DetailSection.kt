package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbolge.pokedex.data.remote.responses.Pokemon
import java.util.*

@Composable
fun DetailSection(modifier: Modifier = Modifier, pokemonInfo: Pokemon) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "#${pokemonInfo.id} ${
            pokemonInfo.name.replaceFirstChar {
                it.titlecase(
                    Locale.ROOT
                )
            }
            }",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
        TypeSection(types = pokemonInfo.types)
        DetailDataSection(
            weight = pokemonInfo.weight,
            height = pokemonInfo.height
        )
        BaseStatsSection(modifier = modifier, pokemonInfo = pokemonInfo)
    }
}