package com.example.musicmapp2.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicmapp2.data.network.ApiService

class HomeViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiService) as T
    }
}