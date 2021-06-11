package com.example.musicmapp2.data.database.entitys

import androidx.room.Embedded
import androidx.room.Relation

data class DatabaseAlbumWithTracks(
    @Embedded
    val album: DatabaseAlbum,
    @Relation(
        parentColumn = "album_url",
        entityColumn = "track_album_url"
    )
    val track: List<DatabaseTrack>
)
