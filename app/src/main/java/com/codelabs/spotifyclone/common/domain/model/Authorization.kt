package com.codelabs.spotifyclone.common.domain.model

data class Authorization(
    val grantType: String?,
    val code: String?,
    val redirectUri: String?,
)
