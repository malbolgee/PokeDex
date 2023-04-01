package com.malbolge.pokedex.ui.mainscreen.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.malbolge.pokedex.data.models.PokeDexListEntry
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun PokeDexEntry(
    modifier: Modifier = Modifier,
    entry: PokeDexListEntry,
    onNavigateToDetails: (String) -> Unit = {}
) {

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .aspectRatio(1f)
            .clickable {
                onNavigateToDetails(entry.pokemonName)
            },
        elevation = 4.dp
    ) {

        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
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

            Text(
                text = entry.pokemonName,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        PokeDexEntry(
            entry = PokeDexListEntry(
                pokemonName = "Malbolge",
                imageUrl = "url",
                number = 1
            ),
        )
    }
}