package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.ui.detailscreen.state.PokemonDetailScreenUiState
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import com.malbolge.pokedex.utils.Resource
import java.util.*

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    uiState: PokemonDetailScreenUiState = PokemonDetailScreenUiState(),
    pokemonName: String,
    dominantColor: Color,
    onBackNavigation: () -> Unit = {}
) {

    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        uiState.onGetPokemonInfo(pokemonName.lowercase(Locale.ROOT))?.let {
            value = it
        }
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.surface,
                        dominantColor
                    )
                )
            )
    ) {
        TopSection(
            modifier = Modifier
                .align(Alignment.TopStart),
            onBackNavigation = onBackNavigation
        )
        StateWrapper(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .align(Alignment.Center),
            pokemonInfo = pokemonInfo
        )
        TopImage(pokemonInfo = pokemonInfo) // must be drawn on top of the stats info
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        PokemonDetailScreen(
            pokemonName = "Pikachu",
            dominantColor = Color.White,
        )
    }
}