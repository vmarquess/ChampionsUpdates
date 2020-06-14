package br.com.victoriasantos.libertadoresupdates.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.PlayerEntity

@Dao
interface PlayerDAO{
    @Query("SELECT * FROM player WHERE id_team = :id_team")
    suspend fun showPlayerByTeam(id_team : String): List<PlayerEntity>

    @Insert()
    suspend fun insertPlayer(player : PlayerEntity)

    @Delete
    suspend fun deletePlayer(player : PlayerEntity)
}