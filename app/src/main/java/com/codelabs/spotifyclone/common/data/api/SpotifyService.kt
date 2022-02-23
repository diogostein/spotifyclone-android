package com.codelabs.spotifyclone.common.data.api

import com.codelabs.spotifyclone.common.data.api.response.DataWrapperResponse
import com.codelabs.spotifyclone.common.data.api.response.PlaylistResponse
import com.codelabs.spotifyclone.common.data.api.response.UserResponse
import retrofit2.http.GET

interface SpotifyService {

    @GET("v1/me")
    suspend fun getMyProfile(): UserResponse

    @GET("v1/me/playlists")
    suspend fun getMyPlaylists(): DataWrapperResponse<PlaylistResponse>

}