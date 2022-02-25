package com.codelabs.spotifyclone.playlist.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.presentation.UiState
import com.codelabs.spotifyclone.databinding.FragmentPlaylistDetailBinding
import com.codelabs.spotifyclone.databinding.FragmentPlaylistListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistDetailFragment : Fragment(R.layout.fragment_playlist_detail) {
    private val viewModel: PlaylistDetailViewModel by viewModels()

    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!

    private var playlistId: String? = null
    //private val playlistTracksAdapter = PlaylistTracksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playlistId = arguments?.getString(PLAYLIST_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        }

        binding.listStateView.apply {
            //setAdapter(playlistTracksAdapter)
            setOnRetryClickListener { viewModel.getPlaylistTracks("") }
        }

        viewModel.detailStateFlow.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    Glide
                        .with(this)
                        .load(state.data?.images?.first()?.url)
                        .transition(
                            DrawableTransitionOptions.withCrossFade(
                                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                            )
                        )
                        .into(binding.ivCollapsing)
                }
                is UiState.Error -> activity?.supportFragmentManager?.popBackStack()
                else -> {}
            }
        }

        if (savedInstanceState == null) {
            viewModel.getPlaylistDetail(playlistId!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PLAYLIST_ID = "PLAYLIST_ID"

        fun newInstance(id: String): PlaylistDetailFragment {
            return PlaylistDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PLAYLIST_ID, id)
                }
            }
        }
    }
}