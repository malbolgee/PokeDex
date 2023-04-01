package com.malbolge.pokedex.utils

import androidx.compose.ui.graphics.Color
import com.malbolge.pokedex.data.remote.responses.Stat
import com.malbolge.pokedex.data.remote.responses.Type
import com.malbolge.pokedex.ui.theme.*
import java.util.*

object ParseUtils {

    fun typeToColor(type: Type): Color {
        return when (type.type.name.lowercase(Locale.ROOT)) {
            "normal" -> TypeNormal
            "fire" -> TypeFire
            "water" -> TypeWater
            "electric" -> TypeElectric
            "grass" -> TypeGrass
            "ice" -> TypeIce
            "fighting" -> TypeFighting
            "poison" -> TypePoison
            "ground" -> TypeGround
            "flying" -> TypeFlying
            "psychic" -> TypePsychic
            "bug" -> TypeBug
            "rock" -> TypeRock
            "ghost" -> TypeGhost
            "dragon" -> TypeDragon
            "dark" -> TypeDark
            "steel" -> TypeSteel
            "fairy" -> TypeFairy
            else -> Color.Black
        }
    }

    fun statToColor(stat: Stat): Color {
        return when (stat.stat.name.lowercase(Locale.ROOT)) {
            "hp" -> HPColor
            "attack" -> AtkColor
            "defense" -> DefColor
            "special-attack" -> SpAtkColor
            "special-defense" -> SpDefColor
            "speed" -> SpdColor
            else -> Color.White
        }
    }

    fun statToAbbr(stat: Stat): String {
        return when (stat.stat.name.lowercase(Locale.ROOT)) {
            "hp" -> "HP"
            "attack" -> "Atk"
            "defense" -> "Def"
            "special-attack" -> "SpAtk"
            "special-defense" -> "SpDef"
            "speed" -> "Spd"
            else -> ""
        }
    }
}