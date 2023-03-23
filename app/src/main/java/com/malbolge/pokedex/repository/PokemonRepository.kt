package com.malbolge.pokedex.repository

import android.util.Log
import com.malbolge.pokedex.data.remote.PokeApi
import com.malbolge.pokedex.data.remote.responses.Pokemon
import com.malbolge.pokedex.data.remote.responses.PokemonList
import com.malbolge.pokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            Log.e("PokemonRepository::getPokemonList", e.stackTraceToString())
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response)
    }


    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            Log.e("PokemonRepository::getPokemonInfo", e.stackTraceToString())
            return Resource.Error(e.stackTraceToString())
        }

        return Resource.Success(response)
    }

}