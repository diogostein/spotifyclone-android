package com.codelabs.spotifyclone.playlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.codelabs.spotifyclone.common.domain.model.Track
import com.codelabs.spotifyclone.common.domain.toMessage
import com.codelabs.spotifyclone.common.presentation.UiState
import com.codelabs.spotifyclone.playlist.domain.usecase.GetPlaylistDetail
import com.codelabs.spotifyclone.playlist.domain.usecase.GetPlaylistTracks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    private val getPlaylistDetail: GetPlaylistDetail,
    private val getPlaylistTracks: GetPlaylistTracks,
) : ViewModel() {

    private val _detailStateFlow = MutableStateFlow<UiState<Playlist>>(UiState.Initial)
    val detailStateFlow: StateFlow<UiState<Playlist>> = _detailStateFlow

    private val _tracksStateFlow = MutableStateFlow<UiState<List<Track>>>(UiState.Initial)
    val tracksStateFlow: StateFlow<UiState<List<Track>>> = _tracksStateFlow

    private val _selectedTrack = MutableLiveData<Track>()
    val selectedTrack: LiveData<Track> = _selectedTrack

    fun getPlaylistDetail(id: String) {
        _detailStateFlow.value = UiState.Loading

        getPlaylistDetail.execute(id).onEach { result ->
            _detailStateFlow.value = when (result) {
                is Result.Success -> UiState.Success(result.data)
                is Result.Error -> UiState.Error()
                is Result.Exception -> UiState.Error(result.cause.toMessage())
            }
        }.launchIn(viewModelScope)
    }

    fun getPlaylistTracks(id: String) {
        _tracksStateFlow.value = UiState.Loading

        getPlaylistTracks.execute(id).onEach { result ->
            _tracksStateFlow.value = when (result) {
                is Result.Success -> UiState.Success(result.data)
                is Result.Error -> UiState.Error()
                is Result.Exception -> UiState.Error(result.cause.toMessage())
            }
        }.launchIn(viewModelScope)
    }

    fun selectTrack(track: Track) {
        _selectedTrack.value = track
    }

}