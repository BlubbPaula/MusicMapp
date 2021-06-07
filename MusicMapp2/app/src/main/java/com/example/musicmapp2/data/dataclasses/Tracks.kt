package com.example.musicmapp2.data.dataclasses

import com.squareup.moshi.Json

data class Tracks(
    val track: List<Track>
)

data class Track(
    val artist: Artist,
    @Json(name = "@attr")
    val attr: Attr,
    val duration: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)

data class Streamable(
    val fulltrack: String,
    @Json(name = "#text")
    val text: String
)

data class Attr(
    val rank: String
)