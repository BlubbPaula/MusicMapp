package com.example.musicmapp2.data.dataclasses


import com.google.gson.annotations.SerializedName

data class Topalbums(
        val album: List<TopAlbum>,
        @SerializedName("@attr")
    val attr: AttrTopalbum
)