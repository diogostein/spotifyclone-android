package com.codelabs.spotifyclone.playlist.data

import com.codelabs.spotifyclone.common.data.api.SpotifyService
import com.codelabs.spotifyclone.common.data.api.response.DataWrapperResponse
import com.codelabs.spotifyclone.common.data.api.response.PlaylistResponse
import com.codelabs.spotifyclone.playlist.domain.PlaylistRemoteDataSource

class PlaylistRemoteDataSourceImpl(
    private val spotifyService: SpotifyService
) : PlaylistRemoteDataSource {

    override suspend fun getMyPlaylists(): DataWrapperResponse<PlaylistResponse> {
        return spotifyService.getMyPlaylists()
    }

}