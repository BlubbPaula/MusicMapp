package com.example.musicmapp2

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.musicmapp2.adapter.AlbumRecycleViewAdapter
import com.example.musicmapp2.adapter.TrackListRecyclerViewAdapter
import com.example.musicmapp2.data.dataclasses.TopAlbum
import com.example.musicmapp2.data.dataclasses.Track

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_baseline_album_24)
            )
            .into(imgView)
    }
}

@BindingAdapter("albumListData")
fun bindAlbumRecyclerView(recyclerView: RecyclerView, data: List<TopAlbum>?) {
    val adapter = recyclerView.adapter as AlbumRecycleViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("trackListData")
fun bindTrackListRecyclerView(recyclerView: RecyclerView, data: List<Track>?) {
    val adapter = recyclerView.adapter as TrackListRecyclerViewAdapter
    adapter.submitList(data)
}