package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class Streamable(
    val fulltrack: String,
    @Json(name = "#text")
    val text: String
)