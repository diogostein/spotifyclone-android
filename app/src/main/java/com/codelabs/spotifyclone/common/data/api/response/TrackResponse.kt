package com.codelabs.spotifyclone.common.data.api.response

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("artists")
    val artists: List<ArtistResponse>?,
    @SerializedName("duration_ms")
    val durationMs: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("uri")
    val uri: String?
)