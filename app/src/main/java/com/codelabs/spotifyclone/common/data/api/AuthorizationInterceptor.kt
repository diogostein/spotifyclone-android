package com.codelabs.spotifyclone.common.data.api

import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val accountPreferencesReader: AccountPreferences.Reader,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", buildBearerToken())
            .build()

        return chain.proceed(request)
    }

    private fun buildBearerToken() = with(accountPreferencesReader) {
        "$tokenType $accessToken"
    }

}