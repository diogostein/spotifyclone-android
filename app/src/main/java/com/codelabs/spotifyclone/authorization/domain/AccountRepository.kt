package com.codelabs.spotifyclone.authorization.domain

import com.codelabs.spotifyclone.common.data.model.Authorization
import com.codelabs.spotifyclone.common.data.model.Token

interface AccountRepository {
    suspend fun getAccessToken(authorization: Authorization): Token
}