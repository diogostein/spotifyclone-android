package com.codelabs.spotifyclone.core.domain.model

data class Token(
    val accessToken: String?,
    val tokenType: String?,
    val scope: String?,
    val expiresIn: Int?,
    val refreshToken: String?
)
