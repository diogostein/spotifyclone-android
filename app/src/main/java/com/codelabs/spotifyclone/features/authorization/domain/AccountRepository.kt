package com.codelabs.spotifyclone.features.authorization.domain

import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.model.Authorization
import com.codelabs.spotifyclone.core.domain.model.Token

interface AccountRepository {
    suspend fun getAccessToken(authorization: Authorization): Result<Nothing, Token>
}