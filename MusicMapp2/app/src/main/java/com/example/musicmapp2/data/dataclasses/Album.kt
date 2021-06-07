package com.example.musicmapp2.data.dataclasses

data class Album(
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val name: String,
    val playcount: String,
    val tags: Tags,
    val tracks: Tracks,
    val url: String,
//    val wiki: Wiki
)

data class Tags(
    val tag: List<Tag>
)

data class Tag(
    val name: String,
    val url: String
)

data class Wiki(
    val content: String,
    val published: String,
    val summary: String
)