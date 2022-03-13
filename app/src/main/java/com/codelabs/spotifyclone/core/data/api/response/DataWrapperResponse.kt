package com.codelabs.spotifyclone.core.data.api.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse<T>(
    @SerializedName("items")
    val items: List<T>
)