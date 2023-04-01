package com.malbolge.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Move(
    val move: MoveX = MoveX(),
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail> = listOf()
)