package com.codelabs.spotifyclone.core.domain.model

data class Authorization(
    val grantType: String?,
    val code: String?,
    val redirectUri: String?,
)
