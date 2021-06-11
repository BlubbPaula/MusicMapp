package com.example.musicmapp2.data.dataclasses

import com.squareup.moshi.Json

data class OpensearchQuery(
    val role: String,
    val searchTerms: String,
    val startPage: String,
    @Json(name = "#text")
    val text: String
)