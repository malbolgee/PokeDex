package com.malbolge.pokedex.ui.mainscreen.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.malbolge.pokedex.data.models.PokeDexListEntry
import com.malbolge.pokedex.repository.PokemonRepository
import com.malbolge.pokedex.ui.mainscreen.state.MainScreenUiState
import com.malbolge.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    private val _mainScreenUiState = MutableStateFlow(
        MainScreenUiState(
            onSearchTextChange = ::onSearchTextChange,
            onEraseSearchText = ::onEraseSearchText,
            onCalculateDominantColor = ::onCalculateDominantColor
        )
    )

    val uiState = _mainScreenUiState.asStateFlow()

    private val _entries = MutableStateFlow(listOf<PokeDexListEntry>())

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            when (val result = repository.getPokemonList(1280, 0)) {
                is Resource.Success -> {
                    val pokeDexListEntries = result.data?.results?.mapIndexed { _, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }

                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                                "pokemon/$number.png"

                        PokeDexListEntry(
                            entry.name.replaceFirstChar { it.uppercaseChar() },
                            url,
                            number.toInt()
                        )
                    }

                    pokeDexListEntries?.let {
                        _entries.value = it
                        _mainScreenUiState.update { state -> state.copy(entries = _entries.value) }
                    }
                }
                else -> {}
            }
        }
    }

    private fun onSearchTextChange(text: String) {
        viewModelScope.launch {
            _mainScreenUiState.update { state ->
                state.copy(
                    searchText = text,
                    entries = if (text.isBlank()) _entries.value else _entries.value
                        .filter { entry ->
                            entry.doesMatchSearchQuery(text)
                        }
                )
            }
        }
    }

    private fun onEraseSearchText() {
        viewModelScope.launch {
            _mainScreenUiState.update { state ->
                state.copy(
                    searchText = "",
                    entries = _entries.value
                )
            }
        }
    }

    private fun onCalculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit = {}) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { pallet ->
            pallet?.dominantSwatch?.rgb?.let { color -> onFinish(Color(color)) }
        }
    }
}