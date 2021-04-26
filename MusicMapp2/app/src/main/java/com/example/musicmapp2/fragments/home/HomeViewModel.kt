package com.example.musicmapp2.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicmapp2.data.dataclasses.TopAlbum
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.network.MusicMappApi
import com.example.musicmapp2.data.response.TopAlbumsResponse
import com.example.musicmapp2.internal.NoConnectivityException
import kotlinx.coroutines.launch

class
HomeViewModel(
//    private val apiService: ApiService
) : ViewModel() {

    private val _topAlbums = MutableLiveData<List<TopAlbum>>()
    val topAlbums: LiveData<List<TopAlbum>> = _topAlbums

    init {
        fetchTopAlbums("Queen")
    }

    private fun fetchTopAlbums(artist: String) {

        viewModelScope.launch {
            try {

                val fetchTopAlbums = MusicMappApi.retrofitService
                    .getTopAlbums(artist)
                    .await()
                _topAlbums.value = fetchTopAlbums.topalbums.album
            } catch (e: NoConnectivityException) {
                Log.e("Connectivity", "No internet connection.")
            }
        }
    }
}