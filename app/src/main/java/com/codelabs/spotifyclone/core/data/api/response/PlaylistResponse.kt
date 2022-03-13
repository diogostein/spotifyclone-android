package com.codelabs.spotifyclone.core.data.api.response

import com.codelabs.spotifyclone.core.domain.model.Playlist
import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    @SerializedName("collaborative")
    val collaborative: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<ImageResponse>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("owner")
    val owner: OwnerResponse?,
    @SerializedName("primary_color")
    val primaryColor: String?,
    @SerializedName("public")
    val public: Boolean?,
    @SerializedName("tracks")
    val tracks: TrackInfoResponse?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)

fun PlaylistResponse.toPlaylist(): Playlist {
    return Playlist(
        id = id,
        name = name,
        uri = uri,
        images = images?.map { it.toImage() },
        ownerName = owner?.displayName
    )
}