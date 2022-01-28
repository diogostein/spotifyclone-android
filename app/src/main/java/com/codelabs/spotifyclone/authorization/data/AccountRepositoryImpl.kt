package com.codelabs.spotifyclone.authorization.data

import com.codelabs.spotifyclone.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.common.data.api.response.toToken
import com.codelabs.spotifyclone.common.data.model.Authorization
import com.codelabs.spotifyclone.common.data.model.Token

class AccountRepositoryImpl(
    private val remoteDataSource: AccountRemoteDataSource,
    private val localDataSource: AccountLocalDataSource
) : AccountRepository {

    override suspend fun getAccessToken(authorization: Authorization): Token {
        val result = remoteDataSource.getAccessToken(authorization)

        return result.toToken().also {
            localDataSource.saveCredentials(it)
        }
    }

}