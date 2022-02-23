package com.codelabs.spotifyclone.common.data.api.response


import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlResponse?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)