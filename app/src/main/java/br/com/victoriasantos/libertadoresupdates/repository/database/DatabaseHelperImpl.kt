package br.com.victoriasantos.libertadoresupdates.repository.database

import br.com.victoriasantos.libertadoresupdates.repository.roomdto.MatchEntity
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.PlayerEntity
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.TeamEntity
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.TeamRankedEntity

class DatabaseHelperImpl(val appDatabase: AndroidRoomDatabase) : DatabaseHelper {

    override suspend fun showPlayerByTeam(id_team: String): List<PlayerEntity> = appDatabase.PlayerDao().showPlayerByTeam(id_team)

    override suspend fun insertPlayer(player: PlayerEntity) = appDatabase.PlayerDao().insertPlayer(player)

    override suspend fun deletePlayer(player: PlayerEntity) = appDatabase.PlayerDao().deletePlayer(player)

    override suspend fun showAllTeam(): Array<TeamEntity> = appDatabase.TeamDao().showAllTeam()

    override suspend fun insertTeam(team: TeamEntity) = appDatabase.TeamDao().insertTeam(team)

    override suspend fun deleteTeam(team: TeamEntity) = appDatabase.TeamDao().deleteTeam(team)

    override suspend fun deleteAllTeamTable() = appDatabase.TeamDao().deleteAlltable()

    override suspend fun showAllTeamRanked(): Array<TeamRankedEntity> = appDatabase.TeamRankedDao().showAllTeamRanked()

    override suspend fun deleteTeamRanked(teamRank: TeamRankedEntity) = appDatabase.TeamRankedDao().deleteTeamRanked(teamRank)

    override suspend fun insertTeamRanked(teamRank: TeamRankedEntity) = appDatabase.TeamRankedDao().insertTeamRanked(teamRank)

    override suspend fun showAllMatch(): Array<MatchEntity> = appDatabase.MatchDAO().showAllMatch()

    override suspend fun showAMatchbyStatus(status: String): Array<MatchEntity> = appDatabase.MatchDAO().showAMatchbyStatus(status)

    override suspend fun insertMatch(match: MatchEntity) = appDatabase.MatchDAO().insertMatch(match)

    override suspend fun deleteMatch(match: MatchEntity) = appDatabase.MatchDAO().deleteMatch(match)

    override suspend fun deleteAllMatchTable() = appDatabase.MatchDAO().deleteAlltable()

    override suspend fun updateMatch(match: MatchEntity) = appDatabase.MatchDAO().updateMatch(match)

    override suspend fun updateTeamRanked(teamRank : TeamRankedEntity) = appDatabase.TeamRankedDao().updateTeamRanked(teamRank)

}