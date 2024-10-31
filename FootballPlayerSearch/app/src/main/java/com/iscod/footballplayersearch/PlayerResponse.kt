package com.iscod.footballplayersearch

data class PlayerResponse(
    val response: List<PlayerData>
)

data class PlayerData(
    val player: Player
)

data class Player(
    val name: String,
    val position: String,
    val nationality: String
)
