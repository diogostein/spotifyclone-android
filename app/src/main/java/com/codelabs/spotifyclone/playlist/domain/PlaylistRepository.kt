package com.codelabs.spotifyclone.playlist.domain

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.codelabs.spotifyclone.common.domain.model.Track

interface PlaylistRepository {
    suspend fun getMyPlaylists(): Result<Nothing, List<Playlist>>
    suspend fun getPlaylistDetail(id: String): Result<Nothing, Playlist>
    suspend fun getPlaylistTracks(id: String): Result<Nothing, List<Track>>
}