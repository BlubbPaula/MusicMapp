package com.example.musicmapp2.data.dataclasses


import com.google.gson.annotations.SerializedName

data class Streamable(
    val fulltrack: String,
    @SerializedName("#text")
    val text: String
)