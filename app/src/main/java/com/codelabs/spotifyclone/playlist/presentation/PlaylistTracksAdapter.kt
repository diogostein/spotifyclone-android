package com.codelabs.spotifyclone.playlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.domain.model.Track
import com.codelabs.spotifyclone.common.helper.GlideHelper
import com.codelabs.spotifyclone.databinding.ItemPlaylistBinding
import com.codelabs.spotifyclone.databinding.ItemTrackBinding

class PlaylistTracksAdapter : RecyclerView.Adapter<PlaylistTracksAdapter.PlaylistTracksViewHolder>() {

    private val items = mutableListOf<Track>()
    private var listener: ((Track) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistTracksViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_track, parent, false).let {
                PlaylistTracksViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: PlaylistTracksViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(tracks: List<Track>) {
        items.addAll(tracks)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Track) -> Unit) {
        PlaylistTracksAdapter@this.listener = listener
    }

    class PlaylistTracksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTrackBinding.bind(itemView)

        fun bind(track: Track, listener: ((Track) -> Unit)? = null) {
            binding.tvTitle.text = track.name
            binding.tvSubtitle.text = track.artists?.first()?.name

            GlideHelper.load(track.album?.images?.first()?.url, binding.ivCover)

            itemView.setOnClickListener { listener?.invoke(track) }
        }
    }

}