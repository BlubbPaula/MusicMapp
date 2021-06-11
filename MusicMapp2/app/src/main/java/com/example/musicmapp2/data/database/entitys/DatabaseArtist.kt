package com.example.musicmapp2.data.database.entitys

import androidx.room.ColumnInfo

data class DatabaseArtist(
    @ColumnInfo(name = "artist_name")
    val artistName: String,
    @ColumnInfo(name = "artist_url")
    val artistUrl: String
)