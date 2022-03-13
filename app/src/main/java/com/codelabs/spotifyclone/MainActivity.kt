package com.codelabs.spotifyclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.codelabs.spotifyclone.features.collapsedplayer.CollapsedPlayerFragment
import com.codelabs.spotifyclone.features.collapsedplayer.CollapsedPlayerViewModel
import com.codelabs.spotifyclone.databinding.ActivityMainBinding
import com.codelabs.spotifyclone.features.playlist.presentation.listing.PlaylistListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val collapsedPlayerViewModel: CollapsedPlayerViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<PlaylistListFragment>(R.id.fcvMain)
                setReorderingAllowed(true)
                addToBackStack(null)
            }

            supportFragmentManager.commit {
                replace<CollapsedPlayerFragment>(R.id.fcvCollapsedPlayer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
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