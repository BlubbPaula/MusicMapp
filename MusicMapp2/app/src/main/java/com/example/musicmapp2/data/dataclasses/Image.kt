package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class Image(
    val size: String,
    @Json(name = "#text")
    val text: String
)