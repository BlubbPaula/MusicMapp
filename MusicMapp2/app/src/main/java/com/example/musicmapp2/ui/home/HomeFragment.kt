package com.example.musicmapp2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.Album
import com.example.musicmapp2.R
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.adapter.RecyclerViewClickListener
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.data.network.ConnectivityInterceptorImpl
import com.example.musicmapp2.data.network.TopAlbumsDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(getContext())
            recyclerView.setHasFixedSize(true)

        val listener = object : RecyclerViewClickListener {
            override fun onClick(view: View?, position: Int) {
                Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show()
            }
        }

        val apiService = ApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val topAlbumsDataSource = TopAlbumsDataSourceImpl(apiService)
//        val textView: TextView = root.findViewById(R.id.textView)

        topAlbumsDataSource.downloadedTopAlbums.observe(this, Observer {
            {}
//            textView.text = it.toString()
            recyclerView.adapter = AlbumRecycleViewAdapter(listener, it!!)

        })

        GlobalScope.launch(Dispatchers.Main) {
            topAlbumsDataSource.fetchTopAlbums("Queen")
        }
        return root
    }
}