package com.codelabs.spotifyclone

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codelabs.spotifyclone.common.Constants
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote

class MainActivity : AppCompatActivity() {
    private var _spotifyAppRemote: SpotifyAppRemote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initAppRemote()
    }

    override fun onStop() {
        super.onStop()
        SpotifyAppRemote.disconnect(_spotifyAppRemote)
    }

    private fun initAppRemote() {
        val connectionParams = ConnectionParams.Builder(Constants.SpotifyApi.clientId)
            .setRedirectUri(Constants.SpotifyApi.redirectUri)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(spotifyAppRemote: SpotifyAppRemote?) {
                _spotifyAppRemote = spotifyAppRemote
                connect()
            }

            override fun onFailure(error: Throwable?) {
                Log.d("DS", error?.message ?: "error")
            }
        })
    }

    private fun connect() {
        _spotifyAppRemote?.playerApi?.subscribeToPlayerState()?.setEventCallback {
            Log.d("DS", "paused: " + it.isPaused)
        }
        _spotifyAppRemote?.playerApi?.play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL")
    }
}