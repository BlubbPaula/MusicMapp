package com.example.musicmapp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicmapp2.data.database.entitys.DatabaseAlbum
import com.example.musicmapp2.data.database.entitys.DatabaseTrack

@Database(
    entities = [
        DatabaseAlbum::class,
        DatabaseTrack::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase : RoomDatabase() {
    abstract val albumDao: AlbumDao
}

private lateinit var INSTANCE: AlbumDatabase

fun getDatabase(context: Context): AlbumDatabase {
    synchronized(AlbumDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AlbumDatabase::class.java,
                "albums"
            ).build()
        }
    }
    return INSTANCE
}











