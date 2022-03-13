package com.codelabs.spotifyclone.core

import android.graphics.Bitmap
import android.util.Log
import com.codelabs.spotifyclone.SpotifyCloneApplication
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Image
import com.spotify.protocol.types.ImageUri
import com.spotify.protocol.types.PlayerState

class SpotifyPlayer {
    enum class ConnectionResult { CONNECTED, FAILURE }

    var spotifyAppRemote: SpotifyAppRemote? = null

    private var _currentUri: String? = null
    val currentUri get() = _currentUri

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
        spotifyAppRemote?.playerApi?.subscribeToPlayerState()
            ?.setEventCallback { eventCallback(it) }
            ?.setErrorCallback { errorCallback(it) }
    }

    fun disconnect() {
        SpotifyAppRemote.disconnect(spotifyAppRemote)
    }

    fun play(uri: String) {
        _currentUri = uri
        spotifyAppRemote?.playerApi?.play(uri)
    }

    fun resume() {
        spotifyAppRemote?.playerApi?.resume()
    }

    fun pause() {
        spotifyAppRemote?.playerApi?.pause()
    }

    fun skipToIndex(uri: String, index: Int) {
        _currentUri = uri
        spotifyAppRemote?.playerApi?.skipToIndex(uri, index)
    }

    fun getImage(
        imageUri: ImageUri,
        dimension: Image.Dimension,
        resultCallback: (Bitmap) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        spotifyAppRemote
            ?.imagesApi
            ?.getImage(imageUri, dimension)
            ?.setResultCallback(resultCallback)
            ?.setErrorCallback(errorCallback)
    }

    companion object {
        private const val TAG = "SpotifyPlayer"
        private val connectionParams = ConnectionParams.Builder(Constants.SpotifyApi.clientId)
            .setRedirectUri(Constants.SpotifyApi.redirectUri)
            .showAuthView(true)
            .build()
    }
}