package com.example.musicmapp2.data.network

import com.example.musicmapp2.data.database.entitys.*
import com.example.musicmapp2.data.dataclasses.Album
import com.example.musicmapp2.data.dataclasses.Results
import com.example.musicmapp2.data.dataclasses.Topalbums
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TopAlbumsResponse(
    val topalbums: Topalbums
)

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    val album: Album
)

@JsonClass(generateAdapter = true)
data class AlbumSearchResponse(
    val results: Results
)

