package com.codelabs.spotifyclone.core.domain.model

data class Album(
    val images: List<Image>?
) {
    val mainImageUrl get(): String? = images?.find { it.url != null }?.url
}
