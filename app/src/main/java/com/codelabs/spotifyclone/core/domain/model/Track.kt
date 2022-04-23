package com.codelabs.spotifyclone.core.domain.model

import java.util.*

data class Track(
    val artists: List<Artist>?,
    val durationMs: Int?,
    val id: String?,
    val name: String?,
    val uri: String?,
    val album: Album?,
    val addedAt: Date?
) {
    val artistNames get(): String? = artists?.map { it.name }?.joinToString(", ")
}