package com.codelabs.spotifyclone.core.data.preferences

import com.codelabs.spotifyclone.core.domain.model.Token

sealed interface AccountPreferences {
    interface Reader {
        val accessToken: String?
        val refreshToken: String?
        val expiresIn: Int?
        val scope: String?
        val tokenType: String?
    }

    interface Editor {
        fun save(token: Token)
        fun clear()
    }
}