package com.malbolge.pokedex.ui.mainscreen.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malbolge.pokedex.R
import com.malbolge.pokedex.ui.theme.PokeDexTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    @StringRes hint: Int,
    onSearch: (String) -> Unit = {}
) {

    var isHintDisplayed by remember {
        mutableStateOf(true)
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onSearch,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )

        if (isHintDisplayed) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                text = stringResource(hint),
                color = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        SearchBar(hint = R.string.search_bar_hint, text = "")
    }
}