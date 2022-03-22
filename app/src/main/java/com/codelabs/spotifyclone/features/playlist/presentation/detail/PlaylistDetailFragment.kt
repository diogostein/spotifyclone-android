package com.codelabs.spotifyclone.features.playlist.presentation.detail

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.palette.graphics.Palette
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.core.domain.model.Track
import com.codelabs.spotifyclone.core.helper.GlideHelper
import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.databinding.FragmentPlaylistDetailBinding
import com.codelabs.spotifyclone.features.collapsedplayer.CollapsedPlayerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaylistDetailFragment : Fragment(R.layout.fragment_playlist_detail) {
    private val playlistDetailViewModel: PlaylistDetailViewModel by viewModels()
    private val collapsedPlayerViewModel: CollapsedPlayerViewModel by activityViewModels()

    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!

    private var playlistId: String? = null
    private var currentPlaylist: Playlist? = null
    private val playlistTracksAdapter = PlaylistTracksAdapter()

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

        binding.fabPlayback.setOnClickListener {
            currentPlaylist?.uri?.let { currentPlaylistUri ->
                if (currentPlaylistUri == collapsedPlayerViewModel.getCurrentUri()) {
                    collapsedPlayerViewModel.resumeOrPause()
                } else {
                    collapsedPlayerViewModel.play(currentPlaylistUri)
                }
            }
        }

        playlistTracksAdapter.setOnItemClickListener(::onItemClickListener)

        binding.listStateView.apply {
            setAdapter(playlistTracksAdapter)
            setOnRetryClickListener { playlistDetailViewModel.getPlaylistTracks(playlistId!!) }
        }

        playlistDetailViewModel.detailStateFlow.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}
                is UiState.Success -> currentPlaylist = state.data?.also { updatePlaylistDetail(it) }
                is UiState.Error -> activity?.supportFragmentManager?.popBackStack()
                else -> {}
            }
        }

        playlistDetailViewModel.tracksStateFlow.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> binding.listStateView.showProgressIndicator()
                is UiState.Success -> {
                    state.data?.let {
                        playlistTracksAdapter.add(it)
                        binding.listStateView.showRecyclerView()
                    }
                }
                is UiState.Error -> binding.listStateView.showError(state.message)
                else -> {}
            }
        }

        collapsedPlayerViewModel.playerState.observe(viewLifecycleOwner) { playerState ->
            currentPlaylist?.uri?.let { currentPlaylistUri ->
                if (currentPlaylistUri == collapsedPlayerViewModel.getCurrentUri()) {
                   if (playerState.isPaused) {
                       binding.fabPlayback.setImageResource(R.drawable.ic_play)
                   } else {
                       binding.fabPlayback.setImageResource(R.drawable.ic_pause)
                   }
                }
            }
        }

        with (playlistDetailViewModel) {
            if (detailStateFlow.value is UiState.Initial
                || tracksStateFlow.value is UiState.Initial) {
                    getPlaylistDetail(playlistId!!)
                    getPlaylistTracks(playlistId!!)
            }
        }
    }

    private fun updatePlaylistDetail(playlist: Playlist) {
        binding.collapsingToolbar.title = playlist.name
        binding.tvPlaylistName.text = playlist.name
        binding.tvUserName.text = playlist.ownerName

        playlist.coverImageUrl?.let { coverImageUrl ->
            GlideHelper.load(coverImageUrl, binding.ivCollapsing)
            GlideHelper.toBitmap(coverImageUrl) { bitmap ->
                Palette.from(bitmap).generate().mutedSwatch?.rgb?.let { color ->
                    binding.appBarLayout.background = GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(color,
                            ContextCompat.getColor(
                                requireContext(), R.color.color_primary_variant))
                    )
                }
            }
        }

        playlist.uri?.let { currentPlaylistUri ->
            if (currentPlaylistUri == collapsedPlayerViewModel.getCurrentUri()
                && collapsedPlayerViewModel.playerState.value?.isPaused == false) {
                binding.fabPlayback.setImageResource(android.R.drawable.ic_media_pause)
            }
        }
    }

    private fun onItemClickListener(track: Track, position: Int) {
        collapsedPlayerViewModel.skipToIndex(currentPlaylist?.uri!!, position)
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