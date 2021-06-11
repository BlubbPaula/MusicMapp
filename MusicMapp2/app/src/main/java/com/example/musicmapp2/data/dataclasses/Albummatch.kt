package com.example.musicmapp2.data.dataclasses

data class Albummatch(
    val artist: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)