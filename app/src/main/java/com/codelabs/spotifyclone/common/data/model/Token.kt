package com.codelabs.spotifyclone.common.data.model

data class Token(
    val accessToken: String?,
    val tokenType: String?,
    val scope: String?,
    val expiresIn: Int?,
    val refreshToken: String?
)
