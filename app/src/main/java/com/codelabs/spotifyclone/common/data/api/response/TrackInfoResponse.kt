package com.codelabs.spotifyclone.common.data.api.response

import com.codelabs.spotifyclone.common.domain.model.Track
import com.codelabs.spotifyclone.common.util.toDateTime
import com.google.gson.annotations.SerializedName

data class TrackInfoResponse(
    @SerializedName("added_at")
    val addedAt: String?,
    @SerializedName("track")
    val track: TrackResponse?,
    @SerializedName("total")
    val total: Int?
)

fun TrackInfoResponse.toTrack(): Track {
    return Track(
        artists = track?.artists?.map { it.toArtist() },
        durationMs =  track?.durationMs,
        id = track?.id,
        name = track?.name,
        uri = track?.uri,
        album = track?.album?.toAlbum(),
        addedAt = addedAt?.toDateTime()
    )
}