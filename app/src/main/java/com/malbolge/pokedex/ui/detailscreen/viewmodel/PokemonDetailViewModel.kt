package com.malbolge.pokedex.ui.detailscreen.viewmodel

import androidx.lifecycle.ViewModel
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.repository.PokemonRepository
import com.malbolge.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}