package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class Track(
    val artist: Artist,
    @Json(name = "@attr")
    val attr: Attr,
    val duration: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)