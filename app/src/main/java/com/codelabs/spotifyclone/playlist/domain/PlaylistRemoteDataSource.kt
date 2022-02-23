package com.codelabs.spotifyclone.playlist.domain

import com.codelabs.spotifyclone.common.data.api.response.DataWrapperResponse
import com.codelabs.spotifyclone.common.data.api.response.PlaylistResponse

interface PlaylistRemoteDataSource {
    suspend fun getMyPlaylists(): DataWrapperResponse<PlaylistResponse>
}