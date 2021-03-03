package com.example.musicmapp2.data.network

import androidx.lifecycle.LiveData
import com.example.musicmapp2.data.response.TopAlbumsResponse

interface TopAlbumsDataSource {
    val downloadedTopAlbums: LiveData<TopAlbumsResponse>

    suspend fun fetchTopAlbums(
        artist: String
    )
}