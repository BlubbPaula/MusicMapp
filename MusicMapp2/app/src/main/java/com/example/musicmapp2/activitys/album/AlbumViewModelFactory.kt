package com.example.musicmapp2.activitys.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicmapp2.data.network.ApiService

class AlbumViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumViewModel(apiService) as T
    }
}