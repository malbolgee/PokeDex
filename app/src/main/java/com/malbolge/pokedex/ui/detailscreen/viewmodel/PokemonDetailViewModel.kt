package com.malbolge.pokedex.ui.detailscreen.viewmodel

import androidx.lifecycle.ViewModel
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.repository.PokemonRepository
import com.malbolge.pokedex.ui.detailscreen.state.PokemonDetailScreenUiState
import com.malbolge.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        PokemonDetailScreenUiState(
            onGetPokemonInfo = ::onGetPokemonInfo
        )
    )

    val uiState = _uiState.asStateFlow()

    private suspend fun onGetPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}