package com.codelabs.spotifyclone.authorization.domain

import com.codelabs.spotifyclone.common.data.api.response.TokenResponse
import com.codelabs.spotifyclone.common.domain.model.Authorization

interface AccountRemoteDataSource {
    suspend fun getAccessToken(authorization: Authorization): TokenResponse
}