package com.malbolge.pokedex.ui.mainscreen.composable

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.malbolge.pokedex.R
import com.malbolge.pokedex.data.models.PokeDexListEntry
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    entry: PokeDexListEntry? = null,
    onDominantColor: (Drawable, (Color) -> Unit) -> Unit = { _, _ -> },
    setDominantColor: (Color) -> Unit = {}
) {

    if (entry == null) {
        LocalContext.current.getDrawable(R.drawable.pikachu)?.let { drawable ->
            onDominantColor(drawable) { color ->
                setDominantColor(color)
            }
        }
        Image(
            modifier = modifier
                .size(120.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.pikachu),
            contentDescription = null
        )
    } else {
        SubcomposeAsyncImage(
            modifier = modifier
                .size(120.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(entry.imageUrl)
                .listener(onSuccess = { _, successResult ->
                    onDominantColor(successResult.drawable) { color ->
                        setDominantColor(color)
                    }
                })
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = entry.pokemonName,
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

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        PokemonImage()
    }
}