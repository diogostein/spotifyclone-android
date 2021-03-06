package com.codelabs.spotifyclone.features.collapsedplayer

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.palette.graphics.Palette
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.core.helper.GlideHelper
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seekBar.apply {
            setOnTouchListener { _, _ ->  true}
            setPadding(0, -8, 0, -8)
        }

        binding.tvTitle.isSelected = true

        viewModel.selectedTrack.observe(viewLifecycleOwner) { track ->
            binding.tvTitle.text = track?.name
            binding.tvSubtitle.text = track?.artists?.joinToString(", ") { it.name }
        }

        viewModel.coverBitmap.observe(viewLifecycleOwner) { bitmap ->
            GlideHelper.load(bitmap, binding.ivCover)
            bitmap?.let {
                Palette.from(it).generate().mutedSwatch?.rgb?.let { color ->
                    binding.flContent.setBackgroundColor(color)
                }
            }
        }

        viewModel.uiProgressState.observe(viewLifecycleOwner) { state ->
            binding.seekBar.progress = state.progress
        }

        viewModel.playerState.observe(viewLifecycleOwner) { playerState ->
            if (playerState.isPaused) {
                binding.ibPlayPause.setImageResource(R.drawable.ic_play)
            } else {
                binding.ibPlayPause.setImageResource(R.drawable.ic_pause)
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