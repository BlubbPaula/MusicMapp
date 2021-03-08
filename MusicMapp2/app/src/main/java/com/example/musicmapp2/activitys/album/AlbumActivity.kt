package com.example.musicmapp2.activitys.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.R
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.adapter.TrackListRecyclerViewAdapter
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.network.ConnectivityInterceptorImpl
import com.example.musicmapp2.fragments.home.HomeViewModel
import com.example.musicmapp2.fragments.home.HomeViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class AlbumActivity : AppCompatActivity() {
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var viewModelFactory: AlbumViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val apiService = ApiService(ConnectivityInterceptorImpl(this))
        viewModelFactory = AlbumViewModelFactory(apiService)
        albumViewModel = ViewModelProvider(this, viewModelFactory).get(AlbumViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTracks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        // Get the Intent that started this activity and extract the string
        val albumInformation = intent.getStringArrayListExtra("AlbumInformation")

        albumViewModel.downloadedAlbum.observe(this, {
            recyclerView.adapter = TrackListRecyclerViewAdapter(it!!)
        })

        GlobalScope.launch(Dispatchers.Main) {
            albumViewModel.fetchAlbum(albumInformation!![0], albumInformation!![1])
        }
    }
}