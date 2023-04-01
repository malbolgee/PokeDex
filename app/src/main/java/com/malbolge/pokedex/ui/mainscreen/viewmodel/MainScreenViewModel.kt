package com.malbolge.pokedex.ui.mainscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malbolge.pokedex.data.models.PokeDexListEntry
import com.malbolge.pokedex.repository.PokemonRepository
import com.malbolge.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    private val _searchText = MutableStateFlow("")

    val searchText
        get() = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)

    val isSearching
        get() = _isSearching.asStateFlow()

    private val _entries = MutableStateFlow(listOf<PokeDexListEntry>())

    init {
        loadPokemon()
    }

    @OptIn(FlowPreview::class)
    val entries = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_entries) { text, entries ->
            if (text.isBlank()) {
                entries
            } else {
                entries.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _entries.value
        )

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
                    }
                }
                else -> {}
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun eraseSearchText() {
        _searchText.value = ""
    }
}