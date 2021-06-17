package com.example.musicmapp2.data.database.entitys

import androidx.room.Embedded
import androidx.room.Relation
import com.example.musicmapp2.data.dataclasses.*
import com.squareup.moshi.Json
import java.util.*

data class DatabaseAlbumWithTracks(
    @Embedded
    val album: DatabaseAlbum,
    @Relation(
        parentColumn = "album_url",
        entityColumn = "track_album_url"
    )
    val tracks: List<DatabaseTrack>
)
