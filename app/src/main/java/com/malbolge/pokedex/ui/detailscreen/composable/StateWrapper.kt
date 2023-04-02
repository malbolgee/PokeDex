package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import com.malbolge.pokedex.utils.Resource

@Composable
fun StateWrapper(
    modifier: Modifier = Modifier,
    pokemonInfo: Resource<Pokemon> = Resource.Success(Pokemon()),
    loadingModifier: Modifier = Modifier
) {

    when (pokemonInfo) {
        is Resource.Success -> {
            DetailCard(modifier = modifier) {
                DetailSection(
                    modifier = modifier.offset(y = (-20).dp),
                    pokemonInfo = pokemonInfo.data!!
                )
            }
        }
        is Resource.Error -> {
            DetailCard(modifier = modifier) {
                Text(
                    text = pokemonInfo.message!!,
                    color = Color.Red,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
                    .size(100.dp)
                    .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokeDexTheme {
        StateWrapper()
    }
}