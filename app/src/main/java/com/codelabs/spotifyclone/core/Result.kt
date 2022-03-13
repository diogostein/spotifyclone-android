package com.codelabs.spotifyclone.core

import com.codelabs.spotifyclone.core.domain.ExceptionCause

sealed class Result<out F, out S> {
    data class Success<S>(val data: S? = null) : Result<Nothing, S>()
    data class Error<F>(val error: F? = null) : Result<F, Nothing>()
    data class Exception(val cause: ExceptionCause) : Result<Nothing, Nothing>()
}



