package com.example.musicmapp2.data.dataclasses

data class Album(
    val artist: String,
    val image: List<Image>,
    val name: String,
    val tracks: Tracks,
    val url: String,
//    val wiki: Wiki
)

