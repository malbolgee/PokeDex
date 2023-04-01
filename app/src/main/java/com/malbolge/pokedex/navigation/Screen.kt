package com.malbolge.pokedex.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object PokemonDetailScreen : Screen("pokemon_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
