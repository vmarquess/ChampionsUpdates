package br.com.victoriasantos.libertadoresupdates.repository.dto

import com.google.gson.annotations.SerializedName

data class MatchesAPIDTO(
   val api: MatchesList

)

data class Team(
    val team_name: String? = null,
    val logo: String? = null
)

data class Score(
    val halftime: String? = null,
    val fulltime: String? = null,
    val extratime: String? = null,
    val penalty: String? = null
)

data class Match(
    @SerializedName("event_date")
    val data: String? = null,
    val round: String? = null,
    val status: String? = null,
    val homeTeam: Team? = null,
    val awayTeam: Team? = null,
    @SerializedName("elapsed")
    val tempo: Int? = null,
    val score: Score? = null

)


data class MatchesList(
    val fixtures: Array<Match>? = null
)