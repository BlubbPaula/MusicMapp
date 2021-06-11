package com.example.musicmapp2.data.dataclasses

import com.squareup.moshi.Json

data class Track(
    val artist: Artist,
    @Json(name = "@attr")
    val trackOrder: TrackOrder,
    val duration: String,
    val name: String,
    val url: String
)