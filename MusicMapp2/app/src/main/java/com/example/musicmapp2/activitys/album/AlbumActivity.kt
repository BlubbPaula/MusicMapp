package com.example.musicmapp2.activitys.album

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

        val albumImgView: ImageView = findViewById(R.id.album_det_image)
        val albumNameView: TextView = findViewById(R.id.album_det_name)
        val artistNameView: TextView = findViewById(R.id.album_det_artist)


        // Get the Intent that started this activity and extract the string
        val albumInformation = intent.getStringArrayListExtra("AlbumInformation")

        albumViewModel.downloadedAlbum.observe(this, {
            recyclerView.adapter = TrackListRecyclerViewAdapter(it!!)
            albumNameView.setText(it!!.album.name)
            artistNameView.setText(it!!.album.artist)

            var imgUri = Uri.parse(it!!.album.image[3].text)

            Glide.with(albumImgView.context)
                    .load(imgUri)
                    .apply(
                            RequestOptions()
                                    .error(R.drawable.ic_baseline_album_24))
                    .into(albumImgView)
        })

        GlobalScope.launch(Dispatchers.Main) {
            albumViewModel.fetchAlbum(albumInformation!![0], albumInformation!![1])
        }
    }
}