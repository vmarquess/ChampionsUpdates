package br.com.victoriasantos.libertadoresupdates.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.victoriasantos.libertadoresupdates.repository.dao.*
import br.com.victoriasantos.libertadoresupdates.repository.roomdto.*


@Database(
    entities = [PlayerEntity::class, TeamEntity::class, TeamRankedEntity::class, MatchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AndroidRoomDatabase() : RoomDatabase() {
    abstract fun PlayerDao(): PlayerDAO
    abstract fun TeamDao(): TeamDAO
    abstract fun TeamRankedDao(): TeamRankedDAO
    abstract fun MatchDAO(): MatchDAO

}


object Database {
    @Volatile
    private lateinit var database: AndroidRoomDatabase

    fun instance(context: Context): AndroidRoomDatabase {
        synchronized(this) {
            if (br.com.victoriasantos.libertadoresupdates.repository.database.Database::database.isInitialized) return database
            database = Room.databaseBuilder(
                context,
                AndroidRoomDatabase::class.java,
                "ChampionLeague"
            ).build()
            return database
        }
    }
}