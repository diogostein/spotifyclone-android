package com.codelabs.spotifyclone.common.domain

import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.SpotifyCloneApplication

sealed class ExceptionCause {
    object NoConnection : ExceptionCause()
    class NetworkFailure(val message: String? = null) : ExceptionCause()
    object Unknown : ExceptionCause()
}

fun ExceptionCause.toMessage(): String {
    return SpotifyCloneApplication.instance.let {
        when (this) {
            is ExceptionCause.NoConnection -> it.getString(R.string.no_internet_connection)
            is ExceptionCause.NetworkFailure -> message ?: it.getString(R.string.server_failure)
            else -> it.getString(R.string.unknown_error)
        }
    }
}