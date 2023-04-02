package com.malbolge.pokedex.ui.mainscreen.composable

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbolge.pokedex.data.models.PokeDexListEntry
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun PokeDexEntry(
    modifier: Modifier = Modifier,
    entry: PokeDexListEntry? = null,
    onDominantColor: (Drawable, (Color) -> Unit) -> Unit = { _, _ -> },
    onNavigateToDetails: (String, Int) -> Unit = { _, _ -> }
) {

    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .aspectRatio(1f)
            .clickable {
                onNavigateToDetails(entry?.pokemonName ?: "Pikachu", dominantColor.toArgb())
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            defaultDominantColor,
                            dominantColor
                        )
                    )
                ),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            PokemonImage(
                modifier = Modifier
                    .align(CenterHorizontally),
                entry = entry,
                onDominantColor = onDominantColor
            ) { color ->
                dominantColor = color
            }

            Text(
                text = entry?.pokemonName ?: "#0 Pikachu",
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
        PokeDexEntry()
    }
}