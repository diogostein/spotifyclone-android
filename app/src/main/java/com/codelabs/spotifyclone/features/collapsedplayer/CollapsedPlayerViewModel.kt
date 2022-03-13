package com.codelabs.spotifyclone.features.collapsedplayer

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelabs.spotifyclone.core.SpotifyPlayer
import com.codelabs.spotifyclone.core.util.ProgressTimeTicker
import com.codelabs.spotifyclone.core.util.toMinutesAndSeconds
import com.spotify.protocol.types.Image
import com.spotify.protocol.types.PlayerState
import com.spotify.protocol.types.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollapsedPlayerViewModel @Inject constructor(
    private val spotifyPlayer: SpotifyPlayer,
    private val progressTimeTicker: ProgressTimeTicker,
) : ViewModel() {

    private val _selectedTrack = MutableLiveData<Track?>()
    val selectedTrack: LiveData<Track?> = _selectedTrack

    private val _coverBitmap = MutableLiveData<Bitmap?>()
    val coverBitmap: LiveData<Bitmap?> = _coverBitmap

    private val _playerState = MutableLiveData<PlayerState>()
    val playerState: LiveData<PlayerState> = _playerState

    private val _uiProgressState = MutableLiveData<UiProgressState>()
    val uiProgressState: LiveData<UiProgressState> = _uiProgressState

    fun connect(callback: ((SpotifyPlayer.ConnectionResult, Throwable?) -> Unit)? = null) {
        disconnect()
        spotifyPlayer.connect { result, throwable ->
            when (result) {
                SpotifyPlayer.ConnectionResult.CONNECTED -> {
                    spotifyPlayer.subscribeToPlayerState(::onPlayerStateEvent, ::onPlayerStateError)
                    progressTimeTicker.setOnProgressTickListener(::onProgressTick)
                    callback?.invoke(result, null)
                }
                SpotifyPlayer.ConnectionResult.FAILURE -> callback?.invoke(result, throwable)
            }

        }
    }

    fun disconnect() {
        progressTimeTicker.dispose()
        spotifyPlayer.disconnect()
    }

    fun play(uri: String) {
        spotifyPlayer.play(uri)
    }

    fun resumeOrPause() {
        if (playerState.value?.isPaused == true) {
            spotifyPlayer.resume()
        } else {
            spotifyPlayer.pause()
        }
    }

    fun skipToIndex(uri: String, index: Int) {
        spotifyPlayer.skipToIndex(uri, index)
    }

    fun getCurrentUri(): String? {
        return spotifyPlayer.currentUri
    }

    private fun onProgressTick(progress: Double, positionInMs: Long, durationInMs: Long) {
        _uiProgressState.value = UiProgressState(
            progress.toInt(),
            positionInMs.toMinutesAndSeconds(),
            durationInMs.toMinutesAndSeconds()
        )
    }

    private fun onPlayerStateEvent(playerState: PlayerState) {
        updateTrack(playerState)
        updateTimeTicker(playerState)
        _playerState.value = playerState
    }

    private fun onPlayerStateError(throwable: Throwable) {
        Log.d("DS", throwable.stackTraceToString())
    }

    private fun updateTimeTicker(playerState: PlayerState) {
        with (playerState) {
            if (isPaused) {
                progressTimeTicker.stop()
            } else {
                progressTimeTicker.start(track.duration, playbackPosition)
            }
        }
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

    data class UiProgressState(
        val progress: Int,
        val elapsedTime: String,
        val duration: String
    )
}