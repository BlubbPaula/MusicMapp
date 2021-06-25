package com.example.musicmapp2.fragments.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.musicmapp2.data.database.getDatabase
import com.example.musicmapp2.data.dataclasses.TopAlbum
import com.example.musicmapp2.repository.AlbumsRepository
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val albumsRepository = AlbumsRepository(getDatabase(application))

    val topAlbums = albumsRepository.topAlbums

    private val _navigateToAlbumDetail = MutableLiveData<TopAlbum?>()
    val navigateToAlbumDetail get() = _navigateToAlbumDetail

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        fetchTopAlbums("Queen")
    }

    private fun fetchTopAlbums(artist: String) {
        viewModelScope.launch {
            try {
                albumsRepository.getTopAlbums(artist)
            } catch (e: IOException) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onDownloadAlbumClicked(album: TopAlbum) {
        viewModelScope.launch {
            albumsRepository.saveTopAlbum(album)
        }
    }

    fun onAlbumClicked(album: TopAlbum) {
        _navigateToAlbumDetail.value = album
    }

    fun onAlbumDetailNavigated() {
        _navigateToAlbumDetail.value = null
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}