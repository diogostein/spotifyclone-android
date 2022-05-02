package com.codelabs.spotifyclone.features.playlist.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.UseCase
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.core.domain.toMessageRes
import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.features.playlist.domain.usecase.GetMyPlaylists
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PlaylistListViewModel @Inject constructor(
    private val getMyPlaylists: GetMyPlaylists
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<UiState<List<Playlist>>>(UiState.Initial)
    val stateFlow: StateFlow<UiState<List<Playlist>>> = _stateFlow

    init {
        getMyPlaylists()
    }

    fun getMyPlaylists() {
        _stateFlow.value = UiState.Loading

        getMyPlaylists(UseCase.Empty).onEach { result ->
            _stateFlow.value = when (result) {
                is Result.Success -> UiState.Success(result.data)
                is Result.Error -> UiState.Error()
                is Result.Exception -> UiState.Error(result.cause.toMessageRes())
            }
        }.launchIn(viewModelScope)
    }

}