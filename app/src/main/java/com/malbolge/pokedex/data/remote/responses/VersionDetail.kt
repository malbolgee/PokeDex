package com.malbolge.pokedex.data.remote.responses


data class VersionDetail(
    val rarity: Int = 0,
    val version: Version = Version()
)