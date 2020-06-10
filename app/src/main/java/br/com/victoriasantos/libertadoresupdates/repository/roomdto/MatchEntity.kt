package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
data class MatchEntity(
    val data: String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "rodada") val rodada: String? = null,
    @ColumnInfo(name = "status") val status: String? = null,
    @ColumnInfo(name = "time_casa") val nome_time_casa: String? = null,
    @ColumnInfo(name = "logo_casa") val logo_time_casa: String? = null,
    @ColumnInfo(name = "time_fora") val nome_time_fora: String? = null,
    @ColumnInfo(name = "logo_fora") val logo_time_fora: String? = null,
    @ColumnInfo(name = "placar") val placar: String? = null,
    @ColumnInfo(name = "placar_intervalo") val placar_intervalo: String? = null,
    @ColumnInfo(name = "placar_prorrogacao") val placar_prorrogacao: String? = null,
    @ColumnInfo(name = "placar_penaltis") val placar_penaltis: String? = null,
    @ColumnInfo(name = "tempo") val tempo: String? = null,
    @ColumnInfo(name = "estadio") val estadio : String? = null,
    @ColumnInfo(name = "arbitro") val arbitro : String? = null,
    @ColumnInfo(name = "data_recup") val data_recup : String? = null

)