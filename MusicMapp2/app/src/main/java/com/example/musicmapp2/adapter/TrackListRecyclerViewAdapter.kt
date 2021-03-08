package com.example.musicmapp2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.R
import com.example.musicmapp2.data.response.AlbumResponse

class TrackListRecyclerViewAdapter(
        private val dataSet: AlbumResponse
) : RecyclerView.Adapter<TrackListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tracknameTextView: TextView

        init {
            tracknameTextView = view.findViewById(R.id.track_name)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_track_layout, viewGroup, false)
        return TrackListRecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = dataSet.album.tracks.track[position]

        viewHolder.tracknameTextView.text = currentItem.name
    }

    override fun getItemCount() = dataSet.album.tracks.track.size
}