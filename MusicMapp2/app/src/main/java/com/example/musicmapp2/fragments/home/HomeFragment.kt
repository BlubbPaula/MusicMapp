package com.example.musicmapp2.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.data.network.ApiService
import com.example.musicmapp2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        binding.recyclerview.adapter = AlbumRecycleViewAdapter()

        return binding.root

//        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerview)
//            recyclerView.layoutManager = LinearLayoutManager(getContext())
//            recyclerView.setHasFixedSize(true)

//        val listener = object : RecyclerViewClickListener {
//            override fun onClick(position: Int, topAlbum: TopAlbum) {
////                Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show()
//
//                val artist = topAlbum.artist.name
//                val albumname = topAlbum.name
//                val albumInformation = arrayListOf<String>(artist, albumname)
//
//                val intent = Intent(getActivity(), AlbumActivity::class.java).apply {
//                    putExtra("AlbumInformation", albumInformation)
//                }
//                startActivity(intent)
//            }
//        }

//        val textView: TextView = root.findViewById(R.id.textView)

//        homeViewModel.downloadedTopAlbums.observe(viewLifecycleOwner, Observer {
//            recyclerView.adapter = AlbumRecycleViewAdapter(listener, it!!)
//        })
    }


}