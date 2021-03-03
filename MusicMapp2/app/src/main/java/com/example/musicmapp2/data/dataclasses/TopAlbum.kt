package com.example.musicmapp2.data.dataclasses


data class TopAlbum(
        val artist: TopAlbumArtist,
        val image: List<Image>,
        val name: String,
        val playcount: Int,
        val url: String
)