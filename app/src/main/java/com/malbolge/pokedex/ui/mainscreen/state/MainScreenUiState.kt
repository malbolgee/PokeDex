package com.malbolge.pokedex.ui.mainscreen.state

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import com.malbolge.pokedex.data.models.PokeDexListEntry

data class MainScreenUiState(
    val searchText: String = "",
    val isSearching: Boolean = false,
    val entries: List<PokeDexListEntry> = listOf(),
    val onSearchTextChange: (String) -> Unit = {},
    val onEraseSearchText: () -> Unit = {},
    val onCalculateDominantColor: (Drawable, (Color) -> Unit) -> Unit = { _, _ -> }
)