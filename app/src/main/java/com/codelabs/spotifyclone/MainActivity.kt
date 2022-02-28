package com.codelabs.spotifyclone

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.codelabs.spotifyclone.collapsedplayer.CollapsedPlayerFragment
import com.codelabs.spotifyclone.collapsedplayer.CollapsedPlayerViewModel
import com.codelabs.spotifyclone.common.SpotifyPlayer
import com.codelabs.spotifyclone.databinding.ActivityMainBinding
import com.codelabs.spotifyclone.playlist.presentation.PlaylistDetailFragment
import com.codelabs.spotifyclone.playlist.presentation.PlaylistDetailViewModel
import com.codelabs.spotifyclone.playlist.presentation.PlaylistListFragment
import com.spotify.protocol.types.PlayerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val playlistDetailViewModel: PlaylistDetailViewModel by viewModels()
    private val collapsedPlayerViewModel: CollapsedPlayerViewModel by viewModels()

    //private val spotifyPlayer = SpotifyPlayer()

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

        playlistDetailViewModel.selectedTrack.observe(this) { track ->
            collapsedPlayerViewModel.play(track.uri ?: "")
        }
    }

    override fun onStart() {
        super.onStart()
        collapsedPlayerViewModel.connect()
    }

    override fun onStop() {
        super.onStop()
        collapsedPlayerViewModel.disconnect()
    }

}