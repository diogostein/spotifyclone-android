package com.codelabs.spotifyclone.authorization.domain

import com.codelabs.spotifyclone.common.data.api.response.TokenResponse
import com.codelabs.spotifyclone.common.data.model.Authorization
import com.codelabs.spotifyclone.common.data.model.Token

interface AccountRemoteDataSource {
    suspend fun getAccessToken(authorization: Authorization): TokenResponse
}