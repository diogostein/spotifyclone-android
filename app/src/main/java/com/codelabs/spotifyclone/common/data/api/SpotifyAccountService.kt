package com.codelabs.spotifyclone.common.data.api

import com.codelabs.spotifyclone.common.data.api.response.TokenResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyAccountService {

    @FormUrlEncoded
    @POST("token")
    suspend fun getAccessToken(
        @Field("code") code: String,
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ) : TokenResponse

    @FormUrlEncoded
    @POST("token")
    fun getRefreshedTokenSync(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ) : Call<TokenResponse>

}