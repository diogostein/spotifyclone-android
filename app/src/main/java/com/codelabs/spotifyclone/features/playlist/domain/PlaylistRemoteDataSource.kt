package com.codelabs.spotifyclone.features.playlist.domain

import com.codelabs.spotifyclone.core.data.api.response.DataWrapperResponse
import com.codelabs.spotifyclone.core.data.api.response.PlaylistResponse
import com.codelabs.spotifyclone.core.data.api.response.TrackInfoResponse

interface PlaylistRemoteDataSource {
    suspend fun getMyPlaylists(): DataWrapperResponse<PlaylistResponse>
    suspend fun getPlaylistDetail(id: String): PlaylistResponse
    suspend fun getPlaylistTracks(id: String): DataWrapperResponse<TrackInfoResponse>
}