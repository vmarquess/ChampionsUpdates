package br.com.victoriasantos.libertadoresupdates.repository.roomdto


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    @ColumnInfo (name = "nome_time") var nome: String? = null,
    @ColumnInfo (name = "nome_estadio") var estadio: String? = null,
    @ColumnInfo (name = "latitude") var latitude: Double? = 0.0,
    @ColumnInfo (name = "longitude") var longitude: Double? = 0.0
)