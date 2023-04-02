package com.malbolge.pokedex.ui.mainscreen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.malbolge.pokedex.R
import com.malbolge.pokedex.ui.mainscreen.viewmodel.MainScreenViewModel
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel,
    onNavigateToDetails: (String, Int) -> Unit = { _, _ -> }
) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                painter = painterResource(R.drawable.pokemon_logo),
                contentDescription = stringResource(R.string.main_logo_description)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                text = viewModel.searchText.collectAsState().value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                hint = R.string.search_bar_hint,
                onSearch = {
                    viewModel.onSearchTextChange(it)
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (viewModel.isSearching.collectAsState().value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Center)
                    )
                }
            } else {
                PokemonEntryGrid(
                    pokeDexList = viewModel.entries.collectAsState().value,
                    onDominantColor = viewModel::calculateDominantColor,
                    onNavigateToDetails = onNavigateToDetails
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        MainScreen(viewModel = viewModel())
    }
}