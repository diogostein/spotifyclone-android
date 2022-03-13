package com.codelabs.spotifyclone.features.authorization.domain

import com.codelabs.spotifyclone.core.data.api.response.TokenResponse
import com.codelabs.spotifyclone.core.domain.model.Authorization

interface AccountRemoteDataSource {
    suspend fun getAccessToken(authorization: Authorization): TokenResponse
}