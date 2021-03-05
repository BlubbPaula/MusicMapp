package com.example.musicmapp2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.response.TopAlbumsResponse
import com.example.musicmapp2.internal.NoConnectivityException

class HomeViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiService) as T
    }
}