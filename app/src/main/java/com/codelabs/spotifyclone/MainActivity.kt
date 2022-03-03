package com.codelabs.spotifyclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codelabs.spotifyclone.collapsedplayer.CollapsedPlayerFragment
import com.codelabs.spotifyclone.collapsedplayer.CollapsedPlayerViewModel
import com.codelabs.spotifyclone.databinding.ActivityMainBinding
import com.codelabs.spotifyclone.playlist.presentation.PlaylistDetailFragment
import com.codelabs.spotifyclone.playlist.presentation.PlaylistDetailViewModel
import com.codelabs.spotifyclone.playlist.presentation.PlaylistListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val collapsedPlayerViewModel: CollapsedPlayerViewModel by viewModels()

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
        collapsedPlayerViewModel.connect()
    }

    override fun onStop() {
        super.onStop()
        collapsedPlayerViewModel.disconnect()
    }

}