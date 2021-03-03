package com.example.musicmapp2.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.response.TopAlbumsResponse
import com.example.musicmapp2.internal.NoConnectivityException

class TopAlbumsDataSourceImpl(
    private val apiService: ApiService
) : TopAlbumsDataSource {

    private val _downloadedTopAlbums = MutableLiveData<TopAlbumsResponse>()
    override val downloadedTopAlbums: LiveData<TopAlbumsResponse>
        get() = _downloadedTopAlbums

    override suspend fun fetchTopAlbums(artist: String) {
        try{
            val fetchTopAlbums = apiService
                .getTopAlbums(artist)
                .await()
            _downloadedTopAlbums.postValue(fetchTopAlbums)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.")
        }
    }
}