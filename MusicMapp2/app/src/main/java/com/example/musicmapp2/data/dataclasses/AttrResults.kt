package com.example.musicmapp2.data.dataclasses


import com.squareup.moshi.Json

data class AttrResults(
    @Json(name = "for")
    val forX: String
)