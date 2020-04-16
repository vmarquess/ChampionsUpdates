package br.com.victoriasantos.libertadoresupdates.repository.dto

import com.google.gson.annotations.SerializedName

data class TimeDTO(
    val name: String? = null,
    val team_id: Int? = null,
    val logo: String? = null,
    val country: String? = null,
    @SerializedName("venue_name")
    val estadio: String? = null,
    @SerializedName("founded")
    val fundacao: String? = null
)

data class TimeListDTO(
    val teams: Array<TimeDTO>? = null
)

data class TimeAPIDTO(
    val api: TimeListDTO
)