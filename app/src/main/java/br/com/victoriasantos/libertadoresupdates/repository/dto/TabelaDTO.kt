package br.com.victoriasantos.libertadoresupdates.repository.dto

data class TableAPIDTO(
    val api: StandingsDTO
)

data class AllDTO(
    val matchsPlayed: Int? = null,
    val win: Int? = null,
    val draw: Int? = null,
    val lose: Int? = null,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0
)

data class TeamRankedDTO(
    val rank: Int? = null,
    val logo: String? = null,
    val teamName: String? = null,
    val all: AllDTO? = null,
    val points: Int? = null,
    val group: String? = null
)


data class StandingsDTO(
    val standings: Array<Array<TeamRankedDTO>>? = null
)