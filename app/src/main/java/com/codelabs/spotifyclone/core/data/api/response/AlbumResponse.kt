package com.codelabs.spotifyclone.core.data.api.response

import com.codelabs.spotifyclone.core.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("images")
    val images: List<ImageResponse>?
)

fun AlbumResponse.toAlbum(): Album {
    return Album(
        images = images?.map { it.toImage() }
    )
}
