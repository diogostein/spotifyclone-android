package com.codelabs.spotifyclone.authorization.domain.usecase

import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.common.Constants
import com.codelabs.spotifyclone.common.Result
import com.codelabs.spotifyclone.common.domain.model.Authorization
import com.codelabs.spotifyclone.common.domain.model.Token
import com.codelabs.spotifyclone.common.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAccessToken(
    private val accountRepository: AccountRepository,
) : UseCase<String, Result<Nothing, Token>> {

    override fun execute(input: String?): Flow<Result<Nothing, Token>> = flow {
        emit(
            accountRepository.getAccessToken(
                Authorization(
                    code = input,
                    grantType = "authorization_code",
                    redirectUri = Constants.SpotifyApi.redirectUri
                )
            )
        )
    }

}