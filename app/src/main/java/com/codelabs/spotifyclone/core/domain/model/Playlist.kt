package com.codelabs.spotifyclone.core.domain.model

data class Playlist(
    val id: String?,
    val name: String?,
    val description: String?,
    val uri: String?,
    val images: List<Image>?,
    val ownerName: String?
) {
    val coverImageUrl get() = images?.find { it.url != null }?.url
}
