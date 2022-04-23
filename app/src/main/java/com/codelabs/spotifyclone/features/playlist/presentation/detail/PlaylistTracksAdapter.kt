package com.codelabs.spotifyclone.features.playlist.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.core.domain.model.Track
import com.codelabs.spotifyclone.core.helper.GlideHelper
import com.codelabs.spotifyclone.databinding.ItemTrackBinding

class PlaylistTracksAdapter :
    RecyclerView.Adapter<PlaylistTracksAdapter.PlaylistTracksViewHolder>() {

    private val items = mutableListOf<Track>()
    private var listener: ((Track, Int) -> Unit)? = null
    private var currentSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistTracksViewHolder {
        return LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_track, parent, false).let {
                PlaylistTracksViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: PlaylistTracksViewHolder, position: Int) {
        holder.bind(items[position], currentSelectedPosition == position)
        holder.itemView.setOnClickListener { listener?.invoke(items[position], position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(tracks: List<Track>, currentTrack: com.spotify.protocol.types.Track?) {
        items.addAll(tracks)
        checkSelectedTrack(currentTrack)
    }

    fun setOnItemClickListener(listener: (Track, Int) -> Unit) {
        PlaylistTracksAdapter@this.listener = listener
    }

    fun checkSelectedTrack(track: com.spotify.protocol.types.Track?) {
        currentSelectedPosition = items.indexOfFirst { it.uri == track?.uri }
        notifyDataSetChanged()
    }

    inner class PlaylistTracksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTrackBinding.bind(itemView)

        fun bind(track: Track, isSelected: Boolean = false) {
            binding.tvTitle.apply {
                text = track.name
                setTextColor(ContextCompat.getColor(context, if (isSelected) R.color.color_secondary else R.color.white))
            }
            binding.tvSubtitle.text = track.artistNames
            GlideHelper.load(track.album?.mainImageUrl, binding.ivCover)
        }
    }

}