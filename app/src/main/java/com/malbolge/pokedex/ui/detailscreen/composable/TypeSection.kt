package com.malbolge.pokedex.ui.detailscreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malbolge.pokedex.data.remote.responses.Type
import com.malbolge.pokedex.data.remote.responses.TypeX
import com.malbolge.pokedex.ui.theme.PokeDexTheme
import com.malbolge.pokedex.utils.ParseUtils
import java.util.*

@Composable
fun TypeSection(types: List<Type>) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        types.forEach {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(ParseUtils.typeToColor(it))
                    .height(35.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = it.type.name.replaceFirstChar { char -> char.titlecase(Locale.ROOT) },
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PokeDexTheme {
        TypeSection(
            types = listOf(
                Type(type = TypeX(name = "grass")),
                Type(type = TypeX(name = "ghost"))
            )
        )
    }
}