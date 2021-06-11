package com.example.musicmapp2.data.database.entitys

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "local_album_table"
)
data class DatabaseAlbum (
    @PrimaryKey
    @ColumnInfo(name = "album_url")
    val albumUrl: String,
    @ColumnInfo(name = "album_artist")
    val albumArtist: String,
    @ColumnInfo(name = "album_name")
    val albumName: String,
    @Embedded
    val albumImage: DatabaseImage
)


