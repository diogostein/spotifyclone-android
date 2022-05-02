package com.codelabs.spotifyclone.features.authorization.domain.usecase

import com.codelabs.spotifyclone.features.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.core.Constants
import com.codelabs.spotifyclone.core.Result
import com.codelabs.spotifyclone.core.domain.model.Authorization
import com.codelabs.spotifyclone.core.domain.model.Token
import com.codelabs.spotifyclone.core.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAccessToken(
    private val accountRepository: AccountRepository,
) : UseCase<String, Result<Nothing, Token>> {

    override fun invoke(input: String): Flow<Result<Nothing, Token>> = flow {
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