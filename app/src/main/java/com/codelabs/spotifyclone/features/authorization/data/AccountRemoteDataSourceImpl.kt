package com.codelabs.spotifyclone.features.authorization.data

import com.codelabs.spotifyclone.features.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.core.Constants
import com.codelabs.spotifyclone.core.data.api.SpotifyAccountService
import com.codelabs.spotifyclone.core.data.api.response.TokenResponse
import com.codelabs.spotifyclone.core.domain.model.Authorization

class AccountRemoteDataSourceImpl(
    private val spotifyAccountService: SpotifyAccountService
) : AccountRemoteDataSource {

    override suspend fun getAccessToken(authorization: Authorization): TokenResponse {
        return spotifyAccountService.getAccessToken(
            code = authorization.code ?: "",
            grantType = authorization.grantType ?: "",
            redirectUri = authorization.redirectUri ?: "",
            clientId = Constants.SpotifyApi.clientId,
            clientSecret = Constants.SpotifyApi.clientSecret
        )
    }

}