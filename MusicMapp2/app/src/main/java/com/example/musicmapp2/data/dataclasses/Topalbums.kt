package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class Topalbums(
        val album: List<TopAlbum>,
        @Json(name = "@attr")
    val attr: AttrTopalbum
)


