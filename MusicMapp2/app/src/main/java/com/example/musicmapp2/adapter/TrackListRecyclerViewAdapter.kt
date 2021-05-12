package com.example.musicmapp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.data.dataclasses.Track
import com.example.musicmapp2.databinding.ItemTrackLayoutBinding

class TrackListRecyclerViewAdapter :
    ListAdapter<Track, TrackListRecyclerViewAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private var binding: ItemTrackLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemTrackLayoutBinding.inflate(LayoutInflater.from(viewGroup.context))
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        viewHolder.bind(currentItem)
    }
}