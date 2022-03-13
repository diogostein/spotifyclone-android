package com.codelabs.spotifyclone.features.playlist.domain

import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.codelabs.spotifyclone.core.domain.model.Track

interface PlaylistRepository {
    suspend fun getMyPlaylists(): Result<Nothing, List<Playlist>>
    suspend fun getPlaylistDetail(id: String): Result<Nothing, Playlist>
    suspend fun getPlaylistTracks(id: String): Result<Nothing, List<Track>>
}