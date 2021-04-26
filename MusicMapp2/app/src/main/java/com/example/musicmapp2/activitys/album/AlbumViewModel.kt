package com.example.musicmapp2.activitys.album

//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.musicmapp2.data.network.ApiService
//import com.example.musicmapp2.data.network.MusicMappApi
//import com.example.musicmapp2.data.response.AlbumResponse
//import com.example.musicmapp2.internal.NoConnectivityException
//
//class AlbumViewModel() : ViewModel() {
//    private val _album = MutableLiveData<AlbumResponse>()
//    val album: LiveData<AlbumResponse>
//        get() = _album
//
//    suspend fun fetchAlbum(artist: String, albumname: String) {
//        try{
//            val fetchAlbum = MusicMappApi.retrofitService
//                    .getAlbumInformation(artist, albumname)
//                    .await()
//            _album.postValue(fetchAlbum)
//        }
//        catch (e: NoConnectivityException) {
//            Log.e("Connectivity", "No internet connection.")
//        }
//    }
//}