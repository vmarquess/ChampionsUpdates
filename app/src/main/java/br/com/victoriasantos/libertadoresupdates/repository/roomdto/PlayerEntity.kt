package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "player", foreignKeys = arrayOf(
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_team"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id_team")  val id_team: Int = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "number") val number: String? = null,
    @ColumnInfo(name = "position") val position: String? = null,
    @ColumnInfo(name = "age") val age: String? = null,
    @ColumnInfo(name = "nationality") val nationality: String? = null
)