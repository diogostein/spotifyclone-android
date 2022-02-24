package com.codelabs.spotifyclone.collapsedplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.databinding.FragmentCollapsedPlayerBinding
import com.codelabs.spotifyclone.databinding.FragmentPlaylistListBinding

class CollapsedPlayerFragment : Fragment(R.layout.fragment_collapsed_player) {

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

        binding.tvTitle.text = "Cachimbo da Paz"
        binding.tvSubtitle.text = "Gabriel O Pensador"

        Glide
            .with(this)
            .load("https://mosaic.scdn.co/300/ab67616d0000b273331434dfd2e4b33a857b4e73ab67616d0000b27385ae0a0092d90eaa37f4661dab67616d0000b273a65c9844d454375a10178690ab67616d0000b273be245e5b673371e6f95b19e2")
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into(binding.ivCover)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}