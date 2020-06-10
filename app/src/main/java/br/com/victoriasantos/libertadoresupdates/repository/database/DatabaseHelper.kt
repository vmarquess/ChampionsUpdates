package br.com.victoriasantos.libertadoresupdates.repository.database

import br.com.victoriasantos.libertadoresupdates.repository.roomdto.MatchEntity
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.PlayerEntity
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.TeamEntity
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.TeamRankedEntity

interface DatabaseHelper {
    suspend fun showPlayerByTeam(id_team : String): List<PlayerEntity>
    suspend fun insertPlayer(player : PlayerEntity)
    suspend fun deletePlayer(player : PlayerEntity)
    suspend fun showAllTeam(): Array<TeamEntity>
    suspend fun insertTeam(team : TeamEntity)
    suspend fun deleteTeam(team : TeamEntity)
    suspend fun deleteAllTeamTable()
    suspend fun showAllTeamRanked(): Array<TeamRankedEntity>
    suspend fun insertTeamRanked(teamRank : TeamRankedEntity)
    suspend fun deleteTeamRanked(teamRank : TeamRankedEntity)
    suspend fun showAllMatch(): Array<MatchEntity>
    suspend fun showAMatchbyStatus(status : String): Array<MatchEntity>
    suspend fun insertMatch(match : MatchEntity)
    suspend fun deleteMatch(match : MatchEntity)
    suspend fun deleteAllMatchTable()
    suspend fun updateMatch(match : MatchEntity)
}