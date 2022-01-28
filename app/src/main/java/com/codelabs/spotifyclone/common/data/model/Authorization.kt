package com.codelabs.spotifyclone.common.data.model

data class Authorization(
    val grantType: String?,
    val code: String?,
    val redirectUri: String?
)
