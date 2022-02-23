package com.codelabs.spotifyclone.common.data.api.response

import com.codelabs.spotifyclone.common.domain.model.Playlist
import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    @SerializedName("collaborative")
    val collaborative: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlResponse?,
    @SerializedName("href")
    val href: String?,
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
    @SerializedName("snapshot_id")
    val snapshotId: String?,
    @SerializedName("tracks")
    val tracks: TrackResponse?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)

fun PlaylistResponse.toPlaylist(): Playlist {
    return Playlist(
        name = name,
        uri = uri,
    )
}