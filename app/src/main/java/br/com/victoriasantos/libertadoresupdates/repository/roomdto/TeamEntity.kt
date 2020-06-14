package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamEntity (
    @ColumnInfo (name = "name") val name: String? = null,
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "logo") val logo: String? = null,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "estadio") val estadio: String? = null,
    @ColumnInfo(name = "fundacao") val fundacao: String? = null
)