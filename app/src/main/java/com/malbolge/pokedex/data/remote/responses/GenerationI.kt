package com.malbolge.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue = RedBlue(),
    val yellow: Yellow = Yellow()
)