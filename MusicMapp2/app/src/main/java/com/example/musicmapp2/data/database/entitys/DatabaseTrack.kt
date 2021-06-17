package com.example.musicmapp2.data.database.entitys

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "tracks_table",
    foreignKeys = [
        ForeignKey(
            entity = DatabaseAlbum::class,
            parentColumns = ["album_url"],
            childColumns = ["track_album_url"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["track_album_url"])]
)
data class DatabaseTrack (
    @PrimaryKey
    @ColumnInfo(name = "track_url")
    val trackUrl: String,
    @ColumnInfo(name = "track_order")
    val trackOrder: String,
    @Embedded
    val trackArtist: DatabaseArtist,
    @ColumnInfo(name = "track_duration")
    val trackDuration: String,
    @ColumnInfo(name = "track_name")
    val trackName: String,
    @ColumnInfo(name = "track_album_url")
    val trackAlbumUrl: String
)
