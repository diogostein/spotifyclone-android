package com.codelabs.spotifyclone.core.data.preferences

import android.content.SharedPreferences
import com.codelabs.spotifyclone.core.domain.model.Token
import java.util.*
import java.util.concurrent.TimeUnit

class AccountPreferencesEditorImpl(
    private val sharedPreferences: SharedPreferences,
) : AccountPreferences.Editor {

    override fun save(token: Token) {
        sharedPreferences.edit().apply {
            putString("access_token", token.accessToken).apply()
            putLong("expires_in", calculateExpirationTime(token.expiresIn ?: 0)).apply()
            putString("scope", token.scope).apply()
            putString("token_type", token.tokenType).apply()

            token.refreshToken?.let { putString("refresh_token", it).apply() }
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