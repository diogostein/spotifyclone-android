package com.codelabs.spotifyclone.features.authorization.data

import com.codelabs.spotifyclone.features.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.features.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.features.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.data.api.response.toToken
import com.codelabs.spotifyclone.core.domain.model.Authorization
import com.codelabs.spotifyclone.core.domain.model.Token
import com.codelabs.spotifyclone.core.domain.ExceptionHandler
import com.codelabs.spotifyclone.core.repositoryResultHandler

class AccountRepositoryImpl(
    private val remoteDataSource: AccountRemoteDataSource,
    private val localDataSource: AccountLocalDataSource,
    private val dataExceptionHandler: ExceptionHandler,
) : AccountRepository {

    override suspend fun getAccessToken(authorization: Authorization): Result<Nothing, Token> {
        return repositoryResultHandler(dataExceptionHandler) {
            val result = remoteDataSource.getAccessToken(authorization)

            val token = result.toToken().also {
                localDataSource.saveCredentials(it)
            }

            Result.Success(token)
        }
    }

}