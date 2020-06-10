package br.com.victoriasantos.libertadoresupdates.repository.dao

import androidx.room.*
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.MatchEntity

@Dao
interface MatchDAO {

    @Query("SELECT * FROM `match`")
    suspend fun showAllMatch(): Array<MatchEntity>

    @Query("SELECT * FROM `match` WHERE status = :status")
    suspend fun showAMatchbyStatus(status : String): Array<MatchEntity>

    @Insert()
    suspend fun insertMatch(match : MatchEntity)

    @Delete
    suspend fun deleteMatch(match : MatchEntity)

    @Query("DELETE FROM `match`")
    suspend fun deleteAlltable()

    @Update()
    suspend fun updateMatch(match : MatchEntity)
}