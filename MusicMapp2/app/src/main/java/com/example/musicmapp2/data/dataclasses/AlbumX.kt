package com.example.musicmapp2.data.dataclasses


data class AlbumX(
    val artist: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)