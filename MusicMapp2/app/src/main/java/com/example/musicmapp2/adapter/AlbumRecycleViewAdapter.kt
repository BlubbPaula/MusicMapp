package com.example.musicmapp2.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.data.dataclasses.TopAlbum
import com.example.musicmapp2.databinding.ItemAlbumLayoutBinding


class AlbumRecycleViewAdapter :
    ListAdapter<TopAlbum, AlbumRecycleViewAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private var binding: ItemAlbumLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topAlbum: TopAlbum) {
            binding.album = topAlbum
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TopAlbum>() {
        override fun areItemsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean {
            return oldItem.url == newItem.url
        }

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemAlbumLayoutBinding.inflate(LayoutInflater.from(viewGroup.context))
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        viewHolder.bind(currentItem)
    }
}