package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.ui.detailscreen.viewmodel.PokemonDetailViewModel
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import com.malbolge.pokedex.utils.Resource
import java.util.*

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    pokemonName: String,
    viewModel: PokemonDetailViewModel,
    onBackNavigation: () -> Unit = {}
) {

    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName.lowercase(Locale.ROOT))
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
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
            viewModel = viewModel()
        )
    }
}