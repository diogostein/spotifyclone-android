package com.codelabs.spotifyclone.core.data.api

import com.codelabs.spotifyclone.core.data.api.response.DataWrapperResponse
import com.codelabs.spotifyclone.core.data.api.response.PlaylistResponse
import com.codelabs.spotifyclone.core.data.api.response.TrackInfoResponse
import com.codelabs.spotifyclone.core.data.api.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("v1/me")
    suspend fun getMyProfile(): UserResponse

    @GET("v1/me/playlists")
    suspend fun getMyPlaylists(): DataWrapperResponse<PlaylistResponse>

    @GET("v1/playlists/{id}")
    suspend fun getPlaylist(
        @Path("id") id: String,
        @Query("fields") fields: String?
    ): PlaylistResponse

    @GET("v1/playlists/{id}/tracks")
    suspend fun getPlaylistTracks(
        @Path("id") id: String,
        @Query("fields") fields: String?
    ): DataWrapperResponse<TrackInfoResponse>

}