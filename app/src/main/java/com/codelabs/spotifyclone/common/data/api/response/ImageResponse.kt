package com.codelabs.spotifyclone.common.data.api.response

import com.codelabs.spotifyclone.common.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)

fun ImageResponse.toImage(): Image {
    return Image(
        url = url,
        width = width,
        height = height
    )
}