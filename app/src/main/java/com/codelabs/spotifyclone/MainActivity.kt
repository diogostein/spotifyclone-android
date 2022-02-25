package com.codelabs.spotifyclone

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codelabs.spotifyclone.collapsedplayer.CollapsedPlayerFragment
import com.codelabs.spotifyclone.common.SpotifyPlayer
import com.codelabs.spotifyclone.databinding.ActivityMainBinding
import com.codelabs.spotifyclone.playlist.presentation.PlaylistDetailFragment
import com.codelabs.spotifyclone.playlist.presentation.PlaylistListFragment
import com.spotify.protocol.types.PlayerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val spotifyPlayer = SpotifyPlayer()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PlaylistListFragment().let {
            supportFragmentManager.beginTransaction()
                .replace(binding.fcvMain.id, it)
                .addToBackStack("PlaylistsFragment")
                .commit()

            it.setOnItemClickListener { id ->
                supportFragmentManager.beginTransaction()
                    .replace(binding.fcvMain.id, PlaylistDetailFragment.newInstance(id))
                    .addToBackStack("PlaylistDetailFragment")
                    .commit()
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fcvCollapsedPlayer.id, CollapsedPlayerFragment())
            .addToBackStack("CollapsedPlayerFragment")
            .commit()

    }

    override fun onStart() {
        super.onStart()
        spotifyPlayer.connect { result, _ ->
            when (result) {
                SpotifyPlayer.ConnectionResult.CONNECTED ->
                    spotifyPlayer.subscribeToPlayerState(::onPlayerStateEvent, ::onPlayerStateError)
                SpotifyPlayer.ConnectionResult.FAILURE -> {}
            }
        }
    }

    override fun onStop() {
        super.onStop()
        spotifyPlayer.disconnect()
    }

    private fun onPlayerStateEvent(playerState: PlayerState) {
        Log.d("DS", playerState.toString())
    }

    private fun onPlayerStateError(throwable: Throwable) {
        Log.d("DS", throwable.stackTraceToString())
    }
}