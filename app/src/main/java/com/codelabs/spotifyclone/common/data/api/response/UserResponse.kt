package com.codelabs.spotifyclone.common.data.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<ImageResponse>?,
    @SerializedName("product")
    val product: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)