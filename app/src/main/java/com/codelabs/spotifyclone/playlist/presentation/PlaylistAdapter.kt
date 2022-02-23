package com.codelabs.spotifyclone.playlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.domain.model.Playlist

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private val items = mutableListOf<Playlist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_playlist, parent, false).let {
                PlaylistViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(playlists: List<Playlist>) {
        items.addAll(playlists)
        notifyDataSetChanged()
    }

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(playlist: Playlist) {
            tvName.text = playlist.name
        }
    }

}