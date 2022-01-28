package com.codelabs.spotifyclone.common.di

import com.codelabs.spotifyclone.common.Constants
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SpotifyModule {

    @Provides
    @Singleton
    fun provideAuthorizationRequest(): AuthorizationRequest {
        return AuthorizationRequest.Builder(
            Constants.SpotifyApi.clientId,
            AuthorizationResponse.Type.CODE,
            Constants.SpotifyApi.redirectUri
        ).also {
            it.setScopes(Constants.AuthorizationScopes.ALL.toTypedArray())
        }.build()
    }

}