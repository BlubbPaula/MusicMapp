package com.example.musicmapp2.fragments.album

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AlbumViewModelFactory(
    private val albumName: String,
    private val albumArtist: String,
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
            return AlbumViewModel(albumName, albumArtist, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}