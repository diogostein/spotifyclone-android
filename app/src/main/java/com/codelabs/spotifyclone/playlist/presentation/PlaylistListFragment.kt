package com.codelabs.spotifyclone.playlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.presentation.UiState
import com.codelabs.spotifyclone.databinding.FragmentPlaylistListBinding
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
                is UiState.Error -> binding.listStateView.showError(state.message)
                else -> {}
            }
        }

        if (savedInstanceState == null) {
            viewModel.getMyPlaylists()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}