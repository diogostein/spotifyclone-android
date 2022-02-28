package com.codelabs.spotifyclone.collapsedplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.SpotifyPlayer
import com.codelabs.spotifyclone.common.helper.GlideHelper
import com.codelabs.spotifyclone.databinding.FragmentCollapsedPlayerBinding

class CollapsedPlayerFragment : Fragment(R.layout.fragment_collapsed_player) {
    private val viewModel: CollapsedPlayerViewModel by activityViewModels()

    private var _binding: FragmentCollapsedPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollapsedPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedTrack.observe(viewLifecycleOwner) { track ->
            binding.tvTitle.text = track.name
            binding.tvSubtitle.text = track.artist.name
        }

        viewModel.coverBitmap.observe(viewLifecycleOwner) { bitmap ->
            GlideHelper.load(bitmap, binding.ivCover)
        }

        viewModel.playerState.observe(viewLifecycleOwner) { playerState ->
            if (playerState.isPaused) {
                binding.ibPlayPause.setImageResource(android.R.drawable.ic_media_play)
            } else {
                binding.ibPlayPause.setImageResource(android.R.drawable.ic_media_pause)
            }
        }

        binding.ibPlayPause.setOnClickListener {
            viewModel.resumeOrPause()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}