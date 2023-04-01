package com.malbolge.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class UltraSunUltraMoon(
    @SerializedName("front_default")
    val frontDefault: String = "",
    @SerializedName("front_female")
    val frontFemale: Any? = null,
    @SerializedName("front_shiny")
    val frontShiny: String = "",
    @SerializedName("front_shiny_female")
    val frontShinyFemale: Any? = null
)