package com.example.musicmapp2.fragments.album

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.musicmapp2.data.database.getDatabase
import com.example.musicmapp2.repository.AlbumsRepository
import kotlinx.coroutines.launch
import java.io.IOException

class AlbumViewModel(
    private val albumName: String,
    private val albumArtist: String,
    application: Application
) : AndroidViewModel(application) {

    private val albumsRepository = AlbumsRepository(getDatabase(application))
    val album = albumsRepository.album

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        fetchAlbum()
    }

    private fun fetchAlbum() {
        viewModelScope.launch {
            try {
                albumsRepository.getAlbum(albumArtist, albumName)
            } catch (e: IOException) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onDownloadButtonClicked() {
        val savealbum = album.value
        if (savealbum != null) {
            viewModelScope.launch {
                albumsRepository.saveAlbum(savealbum)
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}