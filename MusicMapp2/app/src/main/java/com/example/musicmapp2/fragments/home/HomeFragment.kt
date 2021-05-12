package com.example.musicmapp2.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.musicmapp2.adapter.AlbumListener
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        val adapter = AlbumRecycleViewAdapter(AlbumListener { album ->
            homeViewModel.onAlbumClicked(album)
        })

        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        binding.recyclerview.adapter = adapter

        homeViewModel.navigateToAlbumDetail.observe(viewLifecycleOwner, Observer { album ->
            album?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToAlbumFragment(
                        album.name,
                        album.artist.name
                    )
                )
                homeViewModel.onAlbumDetailNavigated()
            }
        })

        return binding.root
    }


}