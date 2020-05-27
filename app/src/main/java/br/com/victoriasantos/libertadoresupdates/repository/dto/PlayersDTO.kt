package br.com.victoriasantos.libertadoresupdates.repository.dto

import com.google.gson.annotations.SerializedName

data class PlayersDTO(
    val api: Players? = null
)

data class Players(
    val players: Array<Player>? = null

)

data class Player(
    @SerializedName("player_name")
    val name: String? = null,
    val number: Int? = null,
    val position: String? = null,
    val age: Int? = null,
    val nationality: String? = null

)