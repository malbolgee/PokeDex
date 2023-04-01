package com.malbolge.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.malbolge.pokedex.ui.detailscreen.composable.PokemonDetailScreen
import com.malbolge.pokedex.ui.detailscreen.viewmodel.PokemonDetailViewModel
import com.malbolge.pokedex.ui.mainscreen.composable.MainScreen
import com.malbolge.pokedex.ui.mainscreen.viewmodel.MainScreenViewModel

@Composable
fun Navigation(
    mainScreenViewModel: MainScreenViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {

        composable(Screen.MainScreen.route) {
            MainScreen(
                viewModel = mainScreenViewModel,
                onNavigateToDetails = {
                    navController.navigate(
                        Screen.PokemonDetailScreen.withArgs(
                            it
                        )
                    ).also {
                        mainScreenViewModel.eraseSearchText()
                    }
                }
            )
        }
        composable(
            "${Screen.PokemonDetailScreen.route}/{pokemon_name}",
            arguments = listOf(
                navArgument("pokemon_name") {
                    type = NavType.StringType
                }
            )
        ) {
            val pokemonName = it.arguments?.getString("pokemon_name") ?: ""
            PokemonDetailScreen(
                pokemonName = pokemonName,
                viewModel = pokemonDetailViewModel,
                onBackNavigation = { navController.popBackStack() },
            )
        }
    }
}