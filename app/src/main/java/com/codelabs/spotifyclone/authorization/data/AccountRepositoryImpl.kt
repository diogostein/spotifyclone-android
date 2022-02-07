package com.codelabs.spotifyclone.authorization.data

import com.codelabs.spotifyclone.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.data.api.response.toToken
import com.codelabs.spotifyclone.common.domain.model.Authorization
import com.codelabs.spotifyclone.common.domain.model.Token
import com.codelabs.spotifyclone.common.domain.ExceptionHandler
import com.codelabs.spotifyclone.common.repositoryResultHandler

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