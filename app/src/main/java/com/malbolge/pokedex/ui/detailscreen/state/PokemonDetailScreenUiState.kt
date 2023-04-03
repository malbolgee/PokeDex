package com.malbolge.pokedex.ui.detailscreen.state

import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.utils.Resource

data class PokemonDetailScreenUiState(
    val onGetPokemonInfo: suspend (String) -> Resource<Pokemon>? = { null }
)
