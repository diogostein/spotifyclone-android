package com.codelabs.spotifyclone.common.data.preferences

import android.content.SharedPreferences
import com.codelabs.spotifyclone.common.data.model.Token
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

class AccountPreferencesEditorImpl(
    private val sharedPreferences: SharedPreferences,
) : AccountPreferences.Editor {

    override fun save(token: Token) {
        sharedPreferences.edit().apply {
            putString("access_token", token.accessToken).apply()
            putString("refresh_token", token.refreshToken).apply()
            putLong("expires_in", calculateExpirationTime(token.expiresIn ?: 0)).apply()
            putString("scope", token.scope).apply()
            putString("token_type", token.tokenType).apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit().apply {
            remove("access_token").apply()
            remove("refresh_token").apply()
            remove("expires_in").apply()
            remove("scope").apply()
            remove("token_type").apply()
        }
    }

    private fun calculateExpirationTime(seconds: Int): Long {
        return Date().time + TimeUnit.MINUTES.toMillis(seconds.toLong())
    }

}