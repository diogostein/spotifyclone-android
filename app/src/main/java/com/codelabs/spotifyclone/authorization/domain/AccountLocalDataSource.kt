package com.codelabs.spotifyclone.authorization.domain

import com.codelabs.spotifyclone.common.Resource
import com.codelabs.spotifyclone.common.data.model.Token

interface AccountLocalDataSource {
    fun saveCredentials(token: Token): Resource<Nothing>
    fun clearCredentials(): Resource<Nothing>
}