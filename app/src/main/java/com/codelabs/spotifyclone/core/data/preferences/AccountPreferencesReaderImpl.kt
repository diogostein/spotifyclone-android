package com.codelabs.spotifyclone.core.data.preferences

import android.content.SharedPreferences

class AccountPreferencesReaderImpl(
    private val sharedPreferences: SharedPreferences,
) : AccountPreferences.Reader {
    override val accessToken: String?
        get() = sharedPreferences.getString("access_token", null)
    override val refreshToken: String?
        get() = sharedPreferences.getString("refresh_token", null)
    override val expiresIn: Int
        get() = sharedPreferences.getInt("expires_in", 0)
    override val scope: String?
        get() = sharedPreferences.getString("scope", null)
    override val tokenType: String?
        get() = sharedPreferences.getString("token_type", null)
}