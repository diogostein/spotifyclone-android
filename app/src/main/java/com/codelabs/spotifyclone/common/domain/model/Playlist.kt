package com.codelabs.spotifyclone.common.domain.model

data class Playlist(
    val name: String?,
    val uri: String?,
    val images: List<Image>?,
    val ownerName: String?
)
