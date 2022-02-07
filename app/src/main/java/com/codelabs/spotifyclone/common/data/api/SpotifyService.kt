package com.codelabs.spotifyclone.common.data.api

import com.codelabs.spotifyclone.common.data.api.response.UserResponse
import retrofit2.http.GET

interface SpotifyService {

    @GET("v1/me")
    suspend fun getMyProfile(): UserResponse

}