package com.example.musicmapp2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.response.TopAlbumsResponse
import com.example.musicmapp2.internal.NoConnectivityException

class
HomeViewModel(
        private val apiService: ApiService
) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    private val _downloadedTopAlbums = MutableLiveData<TopAlbumsResponse>()
    val downloadedTopAlbums: LiveData<TopAlbumsResponse>
        get() = _downloadedTopAlbums

    suspend fun fetchTopAlbums(artist: String) {
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