package com.codelabs.spotifyclone.common.data.api.response

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("href")
    val href: String?,
    @SerializedName("total")
    val total: Int?
)