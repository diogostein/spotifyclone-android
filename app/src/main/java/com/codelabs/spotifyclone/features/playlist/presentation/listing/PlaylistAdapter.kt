package com.codelabs.spotifyclone.features.playlist.presentation.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.core.helper.GlideHelper
import com.codelabs.spotifyclone.databinding.ItemPlaylistBinding

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private val items = mutableListOf<Playlist>()
    private var listener: ((Playlist) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_playlist, parent, false).let {
                PlaylistViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(playlists: List<Playlist>) {
        items.addAll(playlists)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Playlist) -> Unit) {
        PlaylistAdapter@this.listener = listener
    }

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPlaylistBinding.bind(itemView)

        fun bind(playlist: Playlist, listener: ((Playlist) -> Unit)? = null) {
            binding.tvTitle.text = playlist.name
            binding.tvSubtitle.text = playlist.ownerName

            GlideHelper.load(playlist.images?.first()?.url, binding.ivCover)

            itemView.setOnClickListener { listener?.invoke(playlist) }
        }
    }

}