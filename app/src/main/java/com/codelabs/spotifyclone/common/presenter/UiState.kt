package com.codelabs.spotifyclone.common.presenter

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    class Success<T>(val data: T? = null) : UiState<T>()
    class Error(val message: String? = null) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}