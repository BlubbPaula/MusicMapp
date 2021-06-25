package com.example.musicmapp2.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.musicmapp2.R
import com.example.musicmapp2.adapter.TopalbumListener
import com.example.musicmapp2.adapter.TopalbumRecycleViewAdapter
import com.example.musicmapp2.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        val viewModelFactory = SearchViewModelFactory(activity.application)
        ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater)
        val adapter = TopalbumRecycleViewAdapter(TopalbumListener { album, view ->
            when (view.id) {
                R.id.download_album -> {
                    searchViewModel.onDownloadAlbumClicked(album)
                }
                else -> {
                    searchViewModel.onAlbumClicked(album)
                }
            }
        })
        binding.lifecycleOwner = this
        binding.viewModel = searchViewModel
        binding.recyclerview.adapter = adapter

        searchViewModel.navigateToAlbumDetail.observe(viewLifecycleOwner, { album ->
            album?.let {
                this.findNavController().navigate(
                    SearchFragmentDirections.actionNavigationSearchToAlbumFragment(
                        album.name,
                        album.artist.name
                    )
                )
                searchViewModel.onAlbumDetailNavigated()
            }
        })

        searchViewModel.eventNetworkError.observe(viewLifecycleOwner, { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    private fun onNetworkError() {
        if (!searchViewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            searchViewModel.onNetworkErrorShown()
        }
    }


}