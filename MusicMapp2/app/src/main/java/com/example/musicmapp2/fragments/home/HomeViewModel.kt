package com.example.musicmapp2.fragments.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import android.view.View
import com.example.musicmapp2.R
import com.example.musicmapp2.data.database.getDatabase
import com.example.musicmapp2.data.dataclasses.Album
import com.example.musicmapp2.data.network.MusicMappApi
import com.example.musicmapp2.internal.NoConnectivityException
import kotlinx.coroutines.launch
import com.example.musicmapp2.repository.AlbumsRepository

class
HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val albumsRepository = AlbumsRepository(getDatabase(application))

    val localAlbums = albumsRepository.localAlbums

    private val _navigateToAlbumDetail = MutableLiveData<Album?>()
    val navigateToAlbumDetail get() = _navigateToAlbumDetail

    fun onDownloadAlbumClicked(album: Album) {
        viewModelScope.launch {
            albumsRepository.saveAlbum(album)
        }
    }

    fun onAlbumClicked(album: Album) {
        _navigateToAlbumDetail.value = album
    }

    fun onAlbumDetailNavigated() {
        _navigateToAlbumDetail.value = null
    }
}