package com.example.musicmapp2.data.database

import androidx.room.*
import com.example.musicmapp2.data.database.entitys.DatabaseAlbum
import com.example.musicmapp2.data.database.entitys.DatabaseAlbumWithTracks
import com.example.musicmapp2.data.database.entitys.DatabaseTrack

@Dao
abstract class AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun putAlbum(album: List<DatabaseAlbum>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun putTrack(track: List<DatabaseTrack>)

    suspend fun putAlbumWithTracks(albumWithTracks: DatabaseAlbumWithTracks) {
        putAlbum(listOf(albumWithTracks.album))
        putTrack(albumWithTracks.track)
    }

    @Delete
    abstract suspend fun removeAlbums(album: List<DatabaseAlbum>)

    @Delete
    abstract suspend fun removeTracks(track: List<DatabaseTrack>)

    suspend fun removeAlbumWithTracks(albumWithTracks: DatabaseAlbumWithTracks) {
        removeAlbums(listOf(albumWithTracks.album))
        removeTracks(albumWithTracks.track)
    }

    @Query("SELECT * FROM local_album_table")
    abstract suspend fun getAllAlbums(): List<DatabaseAlbum>

    @Transaction
    @Query("SELECT * FROM local_album_table WHERE album_url = :url")
    abstract suspend fun getAlbumWithTracks(url: String): DatabaseAlbumWithTracks?
}