package com.example.musicmapp2.data.dataclasses


import com.google.gson.annotations.SerializedName

data class OpensearchQuery(
    val role: String,
    val searchTerms: String,
    val startPage: String,
    @SerializedName("#text")
    val text: String
)