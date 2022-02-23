package com.codelabs.spotifyclone.common

import android.util.Log
import com.codelabs.spotifyclone.SpotifyCloneApplication
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.PlayerState

class SpotifyPlayer {
    enum class ConnectionResult { CONNECTED, FAILURE }

    var spotifyAppRemote: SpotifyAppRemote? = null

    fun connect(result: (ConnectionResult, Throwable?) -> Unit) {
        disconnect()
        SpotifyAppRemote.connect(
            SpotifyCloneApplication.instance.applicationContext,
            connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote?) {
                    this@SpotifyPlayer.spotifyAppRemote = spotifyAppRemote
                    result(ConnectionResult.CONNECTED, null)
                }
                override fun onFailure(error: Throwable?) {
                    Log.d(TAG, error?.stackTraceToString() ?: "Connection failure")
                    result(ConnectionResult.FAILURE, error)
                }
            }
        )
    }

    fun subscribeToPlayerState(
        eventCallback: (PlayerState) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        spotifyAppRemote?.playerApi?.subscribeToPlayerState()?.apply {
            setEventCallback { eventCallback(it) }
            setErrorCallback { errorCallback(it) }
        }
    }

    fun disconnect() {
        SpotifyAppRemote.disconnect(spotifyAppRemote)
    }

    fun play(uri: String) {
        spotifyAppRemote?.playerApi?.play(uri)
    }

    companion object {
        private const val TAG = "SpotifyPlayer"
        private val connectionParams = ConnectionParams.Builder(Constants.SpotifyApi.clientId)
            .setRedirectUri(Constants.SpotifyApi.redirectUri)
            .showAuthView(true)
            .build()
    }
}