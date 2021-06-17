package com.example.musicmapp2.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.musicmapp2.R
import com.example.musicmapp2.adapter.AlbumListener
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val viewModelFactory = HomeViewModelFactory(activity.application)
        ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        val adapter = AlbumRecycleViewAdapter(AlbumListener { album, view ->
            when (view.id) {
                R.id.download_album -> {
                    homeViewModel.onDownloadAlbumClicked(album)
                }
                else -> {
                    homeViewModel.onAlbumClicked(album)
                }
            }
        })

        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        binding.recyclerview.adapter = adapter

        homeViewModel.navigateToAlbumDetail.observe(viewLifecycleOwner, Observer { album ->
            album?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToAlbumFragment(
                        album.name,
                        album.artist
                    )
                )
                homeViewModel.onAlbumDetailNavigated()
            }
        })

        return binding.root
    }


}