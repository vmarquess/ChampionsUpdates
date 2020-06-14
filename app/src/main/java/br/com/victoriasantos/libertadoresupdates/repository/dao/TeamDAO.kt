package br.com.victoriasantos.libertadoresupdates.repository.dao

import androidx.room.*
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.TeamEntity

@Dao
interface TeamDAO{
    @Query("SELECT * FROM team order by LOWER(name) ASC")
    suspend fun showAllTeam(): Array<TeamEntity>

    @Insert()
    suspend fun insertTeam(team : TeamEntity)

    @Delete
    suspend fun deleteTeam(team : TeamEntity)

    @Query("DELETE FROM team")
    suspend fun deleteAlltable()
}