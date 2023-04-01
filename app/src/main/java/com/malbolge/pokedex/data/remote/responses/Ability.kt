package com.malbolge.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Ability(
    val ability: AbilityX = AbilityX(),
    @SerializedName("is_hidden")
    val isHidden: Boolean = false,
    val slot: Int = 0
)