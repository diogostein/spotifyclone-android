package com.codelabs.spotifyclone.common

import com.codelabs.spotifyclone.BuildConfig
import okhttp3.internal.immutableListOf

object Constants {

    object SpotifyApi {
        const val clientId = BuildConfig.CLIENT_ID
        const val clientSecret = BuildConfig.CLIENT_SECRET
        const val redirectUri = BuildConfig.REDIRECT_URI
    }

    object AuthorizationScopes {
        private val SPOTIFY_CONNECT_SCOPES = listOf(
            "user-modify-playback-state",
            "user-read-currently-playing",
            "user-read-playback-state"
        )

        private val LIBRARY_SCOPES = listOf(
            "user-library-modify",
            "user-library-read"
        )

        private val PLAYBACK_SCOPES = listOf(
            "streaming",
            "app-remote-control"
        )

        private val USERS_SCOPES = listOf(
            "user-read-email",
            "user-read-private",
            "user-read-birthdate"
        )

        private val FOLLOW_SCOPES = listOf(
            "user-follow-read",
            "user-follow-modify"
        )

        private val PLAYLISTS_SCOPES = listOf(
            "playlist-read-private",
            "playlist-read-collaborative",
            "playlist-modify-public",
            "playlist-modify-private"
        )

        private val LISTENING_HISTORY_SCOPES = listOf(
            "user-read-recently-played",
            "user-top-read"
        )

        val ALL = immutableListOf(
            PLAYBACK_SCOPES,
        ).flatten()
    }

}