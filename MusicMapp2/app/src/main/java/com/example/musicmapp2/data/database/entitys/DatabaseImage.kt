package com.example.musicmapp2.data.database.entitys

import androidx.room.ColumnInfo

data class DatabaseImage(
    @ColumnInfo(name = "image_size")
    val imageSize: String,
    @ColumnInfo(name = "image_text")
    val imageText: String
)