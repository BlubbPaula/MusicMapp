package com.example.musicmapp2.fragments.album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import android.widget.Toast
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
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val viewModelFactory =
            AlbumViewModelFactory(arguments.albumName, arguments.albumArtist, activity.application)
        albumViewModel = ViewModelProvider(this, viewModelFactory).get(AlbumViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = albumViewModel
        binding.trackList.adapter = TrackListRecyclerViewAdapter()

        albumViewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    private fun onNetworkError() {
        if (!albumViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            albumViewModel.onNetworkErrorShown()
        }
    }
}