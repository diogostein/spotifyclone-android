package com.codelabs.spotifyclone.core.presentation

import com.codelabs.spotifyclone.R

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    class Success<T>(val data: T? = null) : UiState<T>()
    class Error(val messageRes: Int = R.string.unknown_error) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}