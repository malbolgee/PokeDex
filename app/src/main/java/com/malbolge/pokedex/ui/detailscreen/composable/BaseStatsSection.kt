package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbolge.pokedex.R
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.utils.ParseUtils

@Composable
fun BaseStatsSection(modifier: Modifier = Modifier, pokemonInfo: Pokemon) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.baseStat }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.detail_base_stats),
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        pokemonInfo.stats.forEachIndexed { i, stat ->
            DetailStats(
                name = ParseUtils.statToAbbr(stat),
                value = stat.baseStat,
                maxValue = maxBaseStat,
                color = ParseUtils.statToColor(stat),
                animationDelay = i * 100
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}