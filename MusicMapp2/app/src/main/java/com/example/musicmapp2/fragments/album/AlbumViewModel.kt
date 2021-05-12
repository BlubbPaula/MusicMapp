package com.example.musicmapp2.fragments.album

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicmapp2.data.network.MusicMappApi
import com.example.musicmapp2.data.response.AlbumResponse
import com.example.musicmapp2.internal.NoConnectivityException
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val albumName: String,
    private val albumArtist: String
) : ViewModel() {
    private val _album = MutableLiveData<AlbumResponse>()
    val album: LiveData<AlbumResponse>
        get() = _album

    init {
        fetchAlbum()
    }

    private fun fetchAlbum() {
        viewModelScope.launch {
            try {
                val fetchAlbum = MusicMappApi.retrofitService
                    .getAlbumInformation(albumArtist, albumName)
                    .await()
                _album.postValue(fetchAlbum)
            } catch (e: NoConnectivityException) {
                Log.e("Connectivity", "No internet connection.")
            }
        }
    }
}