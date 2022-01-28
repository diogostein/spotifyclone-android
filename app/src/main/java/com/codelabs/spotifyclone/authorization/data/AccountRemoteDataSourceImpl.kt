package com.codelabs.spotifyclone.authorization.data

import com.codelabs.spotifyclone.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.common.Constants
import com.codelabs.spotifyclone.common.data.api.SpotifyAccountService
import com.codelabs.spotifyclone.common.data.api.response.TokenResponse
import com.codelabs.spotifyclone.common.data.model.Authorization

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