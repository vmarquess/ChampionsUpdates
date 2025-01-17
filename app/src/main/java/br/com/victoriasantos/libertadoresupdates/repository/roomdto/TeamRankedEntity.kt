package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "teamRanked")
data class TeamRankedEntity (
    @ColumnInfo (name = "rank") val rank: Int? = 0,
    @ColumnInfo (name = "nome") val nome: String? = null,
    @PrimaryKey val id: Int? = 0,
    @ColumnInfo (name = "escudo") val escudo: String? = null,
    @ColumnInfo (name = "partidas") val partidas: String? = null,
    @ColumnInfo (name = "vitorias") val vitorias: String? = null,
    @ColumnInfo (name = "empates") val empates: String? = null,
    @ColumnInfo (name = "derrotas") val derrotas: String? = null,
    @ColumnInfo (name = "pontos") val pontos: String? = null,
    @ColumnInfo (name = "grupo") val grupo: String? = null,
    @ColumnInfo (name = "saldo") val saldo: String? = null,
    @ColumnInfo (name = "data_recup") val data_recup: String? = null
)