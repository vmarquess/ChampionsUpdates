package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "event", foreignKeys = arrayOf(ForeignKey(entity = MatchEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("id_match"), onDelete = ForeignKey.CASCADE)))
data class EventEntity(
    @PrimaryKey (autoGenerate = true) val id_event: Int = 0,
    @ColumnInfo (name = "id_match") val id_match: Int = 0,
    @ColumnInfo (name = "tempo") val evento_tempo: String? = null,
    @ColumnInfo (name = "acrescimo") val evento_acrescimo: String? = null,
    @ColumnInfo (name = "teamName") val evento_teamName: String? = null,
    @ColumnInfo (name = "player") val evento_player: String? = null,
    @ColumnInfo (name = "assist") val evento_assist: String? = null,
    @ColumnInfo (name = "type") val evento_type: String? = null,
    @ColumnInfo (name = "detail") val evento_detail: String? = null,
    @ColumnInfo (name = "comments") val evento_comments: String? = null
)