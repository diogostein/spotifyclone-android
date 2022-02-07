package com.codelabs.spotifyclone.common.domain.model

data class Token(
    val accessToken: String?,
    val tokenType: String?,
    val scope: String?,
    val expiresIn: Int?,
    val refreshToken: String?
)
