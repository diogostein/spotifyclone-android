package com.codelabs.spotifyclone.common

import com.codelabs.spotifyclone.common.domain.ExceptionCause

sealed class Result<out F, out S> {
    data class Success<S>(val data: S? = null) : Result<Nothing, S>()
    data class Error<F>(val error: F? = null) : Result<F, Nothing>()
    data class Exception(val cause: ExceptionCause) : Result<Nothing, Nothing>()
}



