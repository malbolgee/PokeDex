package com.malbolge.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
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
                onNavigateToDetails = { name, color ->
                    navController.navigate(
                        Screen.PokemonDetailScreen.withArgs(
                            name, color.toString()
                        )
                    ).also {
                        mainScreenViewModel.eraseSearchText()
                    }
                }
            )
        }
        composable(
            route = "${Screen.PokemonDetailScreen.route}/{pokemonName}/{dominantColor}",
            arguments = listOf(
                navArgument("pokemonName") {
                    type = NavType.StringType
                },
                navArgument("dominantColor") {
                    type = NavType.IntType
                }
            )
        ) {
            val pokemonName = remember {
                it.arguments?.getString("pokemonName") ?: "Pikachu"
            }

            val dominantColor = remember {
                it.arguments?.getInt("dominantColor")?.let { argument -> Color(argument) }
                    ?: Color.White
            }

            PokemonDetailScreen(
                pokemonName = pokemonName,
                dominantColor = dominantColor,
                viewModel = pokemonDetailViewModel,
                onBackNavigation = { navController.popBackStack() },
            )
        }
    }
}