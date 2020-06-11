package br.com.victoriasantos.libertadoresupdates.repository.dao

import androidx.room.*
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.TeamRankedEntity

@Dao
interface TeamRankedDAO{
    @Query("SELECT * FROM teamRanked order by grupo ASC")
    suspend fun showAllTeamRanked(): Array<TeamRankedEntity>

    @Insert()
    suspend fun insertTeamRanked(teamRank : TeamRankedEntity)

    @Delete
    suspend fun deleteTeamRanked(teamRank : TeamRankedEntity)

    @Update
    suspend fun updateTeamRanked(teamRank : TeamRankedEntity)
}