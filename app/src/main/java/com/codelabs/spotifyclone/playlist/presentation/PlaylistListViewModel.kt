package com.codelabs.spotifyclone.playlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.codelabs.spotifyclone.common.domain.toMessage
import com.codelabs.spotifyclone.common.presentation.UiState
import com.codelabs.spotifyclone.playlist.domain.usecase.GetMyPlaylists
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistListViewModel @Inject constructor(
    private val getMyPlaylists: GetMyPlaylists
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<UiState<List<Playlist>>>(UiState.Initial)
    val stateFlow: StateFlow<UiState<List<Playlist>>> = _stateFlow

    fun getMyPlaylists() {
        _stateFlow.value = UiState.Loading

        getMyPlaylists.execute().onEach { result ->
            _stateFlow.value = when (result) {
                is Result.Success -> UiState.Success(result.data)
                is Result.Error -> UiState.Error()
                is Result.Exception -> UiState.Error(result.cause.toMessage())
            }
        }.launchIn(viewModelScope)
    }

}