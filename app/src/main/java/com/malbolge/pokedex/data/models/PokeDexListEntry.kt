package com.malbolge.pokedex.data.models

data class PokeDexListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int
) {

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            pokemonName,
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
