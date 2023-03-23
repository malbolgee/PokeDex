package com.malbolge.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName

data class GenerationIii(
    val emerald: Emerald = Emerald(),
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen = FireredLeafgreen(),
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphire = RubySapphire()
)