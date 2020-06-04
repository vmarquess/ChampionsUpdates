package br.com.victoriasantos.libertadoresupdates.repository.roomdto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "profile")
data class ProfileEntity (
    @PrimaryKey (autoGenerate = true) val uid: Int = 0,
    @ColumnInfo (name = "nome") var nome: String? = null,
    @ColumnInfo (name = "telefone") var telefone: String? = null,
    @ColumnInfo (name = "email") var email: String? = null,
    @ColumnInfo (name = "time") var time: String? = null
)
