package com.codelabs.spotifyclone.authorization.domain

import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.model.Authorization
import com.codelabs.spotifyclone.common.domain.model.Token

interface AccountRepository {
    suspend fun getAccessToken(authorization: Authorization): Result<Nothing, Token>
}