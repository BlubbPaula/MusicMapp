package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class Topalbums(
        val album: List<TopAlbum>,
        @Json(name = "@attr")
    val attr: AttrTopalbum
)

data class AttrTopalbum(
    val artist: String,
    val page: String,
    val perPage: String,
    val total: String,
    val totalPages: String
)

data class TopAlbum(
    val artist: TopAlbumArtist,
    val image: List<Image>,
    val name: String,
    val playcount: Int,
    val url: String
)

data class TopAlbumArtist(
    val name: String,
    val mbid: String,
    val url: String
)