package com.malbolge.pokedex.ui.mainscreen.composable

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malbolge.pokedex.data.models.PokeDexListEntry
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun PokemonEntryGrid(
    modifier: Modifier = Modifier,
    pokeDexList: List<PokeDexListEntry> = getPokeDexEntryList(),
    onDominantColor: (Drawable, (Color) -> Unit) -> Unit = { _, _ -> },
    onNavigateToDetails: (String, Int) -> Unit = { _, _ -> }
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        columns = GridCells.Adaptive(minSize = 140.dp)
    ) {
        items(pokeDexList, key = { it.number }) {
            PokeDexEntry(
                entry = it,
                onDominantColor = onDominantColor,
                onNavigateToDetails = onNavigateToDetails,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        PokemonEntryGrid()
    }
}

private fun getPokeDexEntryList() = List(30) { i ->
    PokeDexListEntry(
        pokemonName = "Pokemon $i",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
            "pokemon/$i.png",
        number = i
    )
}