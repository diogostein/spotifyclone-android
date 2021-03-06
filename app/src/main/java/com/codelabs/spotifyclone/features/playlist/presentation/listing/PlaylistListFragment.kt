package com.codelabs.spotifyclone.features.playlist.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.databinding.FragmentPlaylistListBinding
import com.codelabs.spotifyclone.features.playlist.presentation.detail.PlaylistDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistListFragment : Fragment(R.layout.fragment_playlist_list) {
    private val viewModel: PlaylistListViewModel by viewModels()

    private var _binding: FragmentPlaylistListBinding? = null
    private val binding get() = _binding!!

    private val playlistAdapter = PlaylistAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).apply {
            setTitle(R.string.my_playlists)
            setSupportActionBar(binding.toolbar)
        }

        playlistAdapter.setOnItemClickListener(::onItemClickListener)

        binding.listStateView.apply {
            setAdapter(playlistAdapter)
            setOnRetryClickListener { viewModel.getMyPlaylists() }
        }

        viewModel.stateFlow.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> binding.listStateView.showProgressIndicator()
                is UiState.Success -> {
                    playlistAdapter.add(state.data!!)
                    binding.listStateView.showRecyclerView()
                }
                is UiState.Error -> binding.listStateView.showError(state.messageRes)
                else -> {}
            }
        }
    }

    private fun onItemClickListener(playlist: Playlist) {
        activity?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMain, PlaylistDetailFragment.newInstance(playlist.id!!))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}