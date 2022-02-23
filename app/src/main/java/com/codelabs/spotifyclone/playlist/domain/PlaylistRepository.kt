package com.codelabs.spotifyclone.playlist.domain

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.model.Playlist

interface PlaylistRepository {
    suspend fun getMyPlaylists(): Result<Nothing, List<Playlist>>
}