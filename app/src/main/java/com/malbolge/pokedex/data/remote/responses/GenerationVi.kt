package com.malbolge.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire = OmegarubyAlphasapphire(),
    @SerializedName("x-y")
    val xY: XY = XY()
)