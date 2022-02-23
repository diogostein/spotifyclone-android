package com.codelabs.spotifyclone.common.data.api.response

import com.google.gson.annotations.SerializedName

data class ExternalUrlResponse(
    @SerializedName("spotify")
    val spotify: String?
)