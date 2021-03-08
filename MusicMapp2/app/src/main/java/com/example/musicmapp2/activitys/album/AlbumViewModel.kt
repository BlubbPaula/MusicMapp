package com.example.musicmapp2.activitys.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.response.AlbumResponse
import com.example.musicmapp2.data.response.TopAlbumsResponse
import com.example.musicmapp2.internal.NoConnectivityException

class AlbumViewModel(
        private val apiService: ApiService
) : ViewModel() {

    private val _downloadedAlbum = MutableLiveData<AlbumResponse>()
    val downloadedAlbum: LiveData<AlbumResponse>
        get() = _downloadedAlbum

    suspend fun fetchAlbum(artist: String, albumname: String) {
        try{
            val fetchAlbum = apiService
                    .getAlbumInformation(artist, albumname)
                    .await()
            _downloadedAlbum.postValue(fetchAlbum)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.")
        }
    }
}