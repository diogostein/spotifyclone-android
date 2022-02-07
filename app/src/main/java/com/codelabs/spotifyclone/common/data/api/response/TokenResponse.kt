package com.codelabs.spotifyclone.common.data.api.response

import com.codelabs.spotifyclone.common.domain.model.Token
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String
)

fun TokenResponse.toToken(): Token {
    return Token(
        accessToken = accessToken,
        tokenType = tokenType,
        scope = scope,
        expiresIn = expiresIn,
        refreshToken = refreshToken
    )
}
