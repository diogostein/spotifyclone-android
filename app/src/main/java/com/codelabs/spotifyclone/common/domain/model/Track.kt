package com.codelabs.spotifyclone.common.domain.model

import java.util.*

data class Track(
    val artists: List<Artist>?,
    val durationMs: Int?,
    val id: String?,
    val name: String?,
    val uri: String?,
    val addedAt: Date?
)