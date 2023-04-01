package com.malbolge.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int = 0,
    val version: Version = Version()
)