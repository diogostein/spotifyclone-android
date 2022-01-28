package com.codelabs.spotifyclone.common

sealed class Resource<out T> {
    class Success<T>(val data: T? = null) : Resource<T>()
    class Error(val message: String? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}