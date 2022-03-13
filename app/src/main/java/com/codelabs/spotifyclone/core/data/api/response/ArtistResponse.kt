package com.codelabs.spotifyclone.core.data.api.response


import com.codelabs.spotifyclone.core.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("name")
    val name: String?
)

fun ArtistResponse.toArtist(): Artist {
    return Artist(
        name = name
    )
}