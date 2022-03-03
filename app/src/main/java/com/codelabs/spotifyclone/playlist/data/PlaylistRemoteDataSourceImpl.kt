package com.codelabs.spotifyclone.playlist.data

import com.codelabs.spotifyclone.common.data.api.SpotifyService
import com.codelabs.spotifyclone.common.data.api.response.DataWrapperResponse
import com.codelabs.spotifyclone.common.data.api.response.PlaylistResponse
import com.codelabs.spotifyclone.common.data.api.response.TrackInfoResponse
import com.codelabs.spotifyclone.playlist.domain.PlaylistRemoteDataSource

class PlaylistRemoteDataSourceImpl(
    private val spotifyService: SpotifyService
) : PlaylistRemoteDataSource {

    override suspend fun getMyPlaylists(): DataWrapperResponse<PlaylistResponse> {
        return spotifyService.getMyPlaylists()
    }

    override suspend fun getPlaylistDetail(id: String): PlaylistResponse {
        return spotifyService.getPlaylist(id, "id,name,owner(display_name),images,uri")
    }

    override suspend fun getPlaylistTracks(id: String): DataWrapperResponse<TrackInfoResponse> {
        return spotifyService.getPlaylistTracks(id,
            "items(added_at,track(id,name,images,uri,duration_ms,artists(name),album(images)))")
    }

}