package com.example.musicmapp2.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.activitys.album.AlbumActivity
import com.example.musicmapp2.R
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.adapter.RecyclerViewClickListener
import com.example.musicmapp2.data.dataclasses.TopAlbum
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.network.ConnectivityInterceptorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val apiService = ApiService(ConnectivityInterceptorImpl(this.requireContext()))
        viewModelFactory = HomeViewModelFactory(apiService)
        homeViewModel =
                ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(getContext())
            recyclerView.setHasFixedSize(true)

        val listener = object : RecyclerViewClickListener {
            override fun onClick(position: Int, topAlbum: TopAlbum) {
//                Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show()

                val artist = topAlbum.artist.name
                val albumname = topAlbum.name
                val albumInformation = arrayListOf<String>(artist, albumname)

                val intent = Intent(getActivity(), AlbumActivity::class.java).apply {
                    putExtra("AlbumInformation", albumInformation)
                }
                startActivity(intent)
            }
        }

//        val textView: TextView = root.findViewById(R.id.textView)

        homeViewModel.downloadedTopAlbums.observe(this, Observer {
            recyclerView.adapter = AlbumRecycleViewAdapter(listener, it!!)
        })

        GlobalScope.launch(Dispatchers.Main) {
            homeViewModel.fetchTopAlbums("Queen")
        }

        return root
    }


}