package com.example.musicmapp2.fragments.album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicmapp2.adapter.TrackListRecyclerViewAdapter
import com.example.musicmapp2.databinding.AlbumFragmentBinding

class AlbumFragment : Fragment() {

    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AlbumFragmentBinding.inflate(inflater)
        val arguments = AlbumFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = AlbumViewModelFactory(arguments.albumName, arguments.albumArtist)
        albumViewModel = ViewModelProvider(this, viewModelFactory).get(AlbumViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = albumViewModel
        binding.trackList.adapter = TrackListRecyclerViewAdapter()

        return binding.root
    }
}