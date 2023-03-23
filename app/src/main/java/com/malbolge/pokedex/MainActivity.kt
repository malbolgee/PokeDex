package com.malbolge.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.malbolge.pokedex.navigation.Navigation
import com.malbolge.pokedex.ui.detailscreen.viewmodel.PokemonDetailViewModel
import com.malbolge.pokedex.ui.mainscreen.viewmodel.MainScreenViewModel
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainScreenViewModel: MainScreenViewModel by viewModels()
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexTheme {
                Navigation(
                    mainScreenViewModel = mainScreenViewModel,
                    pokemonDetailViewModel = pokemonDetailViewModel
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokeDexTheme {
        Greeting("Android")
    }
}