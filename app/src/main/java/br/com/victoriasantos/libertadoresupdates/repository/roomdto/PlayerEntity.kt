package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "player")
data class PlayerEntity(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo (name = "name") val name: String? = null,
    @ColumnInfo (name = "number") val number: String? = null,
    @ColumnInfo (name = "position") val position: String? = null,
    @ColumnInfo (name = "age") val age: String? = null,
    @ColumnInfo (name = "nationality") val nationality: String? = null
)