package com.example.musicmapp2.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musicmapp2.data.database.entitys.DatabaseAlbum
import com.example.musicmapp2.data.database.entitys.DatabaseAlbumWithTracks
import com.example.musicmapp2.data.database.entitys.DatabaseTrack

@Dao
abstract class AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun putAlbum(album: List<DatabaseAlbum>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun putTrack(tracks: List<DatabaseTrack>)

    suspend fun putAlbumWithTracks(albumWithTracks: DatabaseAlbumWithTracks) {
        putAlbum(listOf(albumWithTracks.album))
        putTrack(albumWithTracks.tracks)
    }

    @Delete
    abstract suspend fun removeAlbums(album: List<DatabaseAlbum>)

    @Delete
    abstract suspend fun removeTracks(tracks: List<DatabaseTrack>)

    suspend fun removeAlbumWithTracks(albumWithTracks: DatabaseAlbumWithTracks) {
        removeAlbums(listOf(albumWithTracks.album))
        removeTracks(albumWithTracks.tracks)
    }

    @Transaction
    @Query("SELECT * FROM local_album_table")
    abstract fun getAllAlbumsWithTracks(): LiveData<List<DatabaseAlbumWithTracks>>

    @Transaction
    @Query("SELECT * FROM local_album_table WHERE album_url = :url")
    abstract suspend fun getAlbumWithTracks(url: String): DatabaseAlbumWithTracks?
}