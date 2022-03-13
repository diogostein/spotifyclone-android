package com.codelabs.spotifyclone.core.domain.model

data class Playlist(
    val id: String?,
    val name: String?,
    val uri: String?,
    val images: List<Image>?,
    val ownerName: String?
)
