package com.codelabs.spotifyclone.core.domain

import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.SpotifyCloneApplication

sealed class ExceptionCause {
    object NoConnection : ExceptionCause()
    class NetworkFailure(val message: String? = null) : ExceptionCause()
    object Unknown : ExceptionCause()
}

fun ExceptionCause.toMessageRes(): Int {
    return when (this) {
        is ExceptionCause.NoConnection -> R.string.no_internet_connection
        is ExceptionCause.NetworkFailure -> R.string.server_failure
        else -> R.string.unknown_error
    }
}