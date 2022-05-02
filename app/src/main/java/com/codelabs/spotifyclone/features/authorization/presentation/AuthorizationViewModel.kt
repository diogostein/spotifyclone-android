package com.codelabs.spotifyclone.features.authorization.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.spotifyclone.features.authorization.domain.usecase.GetAccessToken
import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.core.domain.toMessageRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessToken
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<UiState<Nothing>>(UiState.Initial)
    val stateFlow: StateFlow<UiState<Nothing>> = _stateFlow

    fun getAccessToken(responseCode: String) {
        _stateFlow.value = UiState.Loading

        getAccessTokenUseCase(responseCode).onEach { result ->
            _stateFlow.value = when (result) {
                is Result.Success -> UiState.Success()
                is Result.Error -> UiState.Error()
                is Result.Exception -> UiState.Error(result.cause.toMessageRes())
            }
        }.launchIn(viewModelScope)
    }

}