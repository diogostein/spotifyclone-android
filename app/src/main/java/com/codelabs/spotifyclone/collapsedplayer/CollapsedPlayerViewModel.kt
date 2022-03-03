package com.codelabs.spotifyclone.collapsedplayer

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelabs.spotifyclone.common.SpotifyPlayer
import com.spotify.protocol.types.Image
import com.spotify.protocol.types.PlayerState
import com.spotify.protocol.types.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollapsedPlayerViewModel @Inject constructor(
    private val spotifyPlayer: SpotifyPlayer,
) : ViewModel() {

    private val _selectedTrack = MutableLiveData<Track?>()
    val selectedTrack: LiveData<Track?> = _selectedTrack

    private val _coverBitmap = MutableLiveData<Bitmap?>()
    val coverBitmap: LiveData<Bitmap?> = _coverBitmap

    private val _playerState = MutableLiveData<PlayerState>()
    val playerState: LiveData<PlayerState> = _playerState

    fun connect(callback: ((SpotifyPlayer.ConnectionResult, Throwable?) -> Unit)? = null) {
        disconnect()
        spotifyPlayer.connect { result, throwable ->
            when (result) {
                SpotifyPlayer.ConnectionResult.CONNECTED -> {
                    spotifyPlayer.subscribeToPlayerState(::onPlayerStateEvent, ::onPlayerStateError)
                    callback?.invoke(result, null)
                }
                SpotifyPlayer.ConnectionResult.FAILURE -> callback?.invoke(result, throwable)
            }

        }
    }

    fun disconnect() {
        spotifyPlayer.disconnect()
    }

    fun play(uri: String) {
        Log.d("DS", uri)
        spotifyPlayer.play(uri)
    }

    fun resumeOrPause() {
        when (spotifyPlayer.currentState) {
            SpotifyPlayer.State.RESUMED -> spotifyPlayer.pause()
            SpotifyPlayer.State.PAUSED -> spotifyPlayer.resume()
            else -> {}
        }
    }

    fun skipToIndex(uri: String, index: Int) {
        spotifyPlayer.skipToIndex(uri, index)
    }

    fun getCurrentUri(): String? {
        return spotifyPlayer.currentUri
    }

    private fun onPlayerStateEvent(playerState: PlayerState) {
        updateTrack(playerState)
        _playerState.value = playerState
    }

    private fun updateTrack(playerState: PlayerState) {
        Log.d("DS", playerState.toString())
        if (playerState.track != null && _selectedTrack.value?.uri != playerState.track.uri) {
            _selectedTrack.value = playerState.track

            spotifyPlayer.getImage(playerState.track.imageUri, Image.Dimension.SMALL, {
                _coverBitmap.value = it
            }, {
                _coverBitmap.value = null
            })
        }
    }

    private fun onPlayerStateError(throwable: Throwable) {
        Log.d("DS", throwable.stackTraceToString())
    }
}