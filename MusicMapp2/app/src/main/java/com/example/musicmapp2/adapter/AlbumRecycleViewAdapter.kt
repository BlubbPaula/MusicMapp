package com.example.musicmapp2.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.musicmapp2.R
import com.example.musicmapp2.data.dataclasses.TopAlbum
import com.example.musicmapp2.data.response.TopAlbumsResponse
import java.net.URI

class AlbumRecycleViewAdapter internal constructor(private val mListener: RecyclerViewClickListener,
                                                   private val dataSet: TopAlbumsResponse) :
    RecyclerView.Adapter<AlbumRecycleViewAdapter.ViewHolder>()  {


    class ViewHolder(view: View, mListener: RecyclerViewClickListener) : RecyclerView.ViewHolder(view) {
        val parentLayout: ViewGroup
        val nameTextView: TextView
        val artistTextView: TextView
        val imageView: ImageView

        init {
            nameTextView = view.findViewById(R.id.album_name)
            artistTextView = view.findViewById(R.id.artist_name)
            imageView = view.findViewById(R.id.album_image)
            parentLayout = view.findViewById(R.id.item_album_layout)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_album_layout, viewGroup, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem: TopAlbum = dataSet.topalbums.album[position]

        viewHolder.nameTextView.text = currentItem.name
        viewHolder.artistTextView.text = currentItem.artist.name
//        viewHolder.imageView.setImageResource()
        val imgUri =
            Uri.parse(currentItem.image[1].text)
        Glide.with(viewHolder.imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .error(R.drawable.ic_baseline_album_24))
            .into(viewHolder.imageView)
    }

    override fun getItemCount() = dataSet.topalbums.album.size
}