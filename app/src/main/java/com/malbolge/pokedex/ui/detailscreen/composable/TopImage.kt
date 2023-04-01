package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import com.malbolge.pokedex.utils.Resource

@Composable
fun TopImage(pokemonInfo: Resource<Pokemon> = Resource.Loading()) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (pokemonInfo is Resource.Success) {
            pokemonInfo.data?.sprites?.let {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .offset(y = 20.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.frontDefault)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Fit,
                    contentDescription = pokemonInfo.data.name,
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .scale(0.5f),
                            color = MaterialTheme.colors.primary
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        TopImage()
    }
}