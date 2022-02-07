package com.codelabs.spotifyclone.common.data.api

import com.codelabs.spotifyclone.common.Constants
import com.codelabs.spotifyclone.common.data.api.response.toToken
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.HttpURLConnection

class TokenRefreshAuthenticator(
    private val spotifyAccountService: SpotifyAccountService,
    private val accountPreferencesReader: AccountPreferences.Reader,
    private val accountPreferencesEditor: AccountPreferences.Editor
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {

            // Make a synchronous request to get a new access token
            val request = spotifyAccountService.getRefreshToken(
                grantType = "refresh_token",
                refreshToken = accountPreferencesReader.refreshToken ?: "",
                clientId = Constants.SpotifyApi.clientId,
                clientSecret = Constants.SpotifyApi.clientSecret,
            ).execute()

            if (request.isSuccessful) {
                request.body()?.let {
                    accountPreferencesEditor.save(it.toToken())
                }

                return response
                    .request
                    .newBuilder()
                    .header("Authorization", buildBearerToken())
                    .build()
            }
        }

        return null
    }

    private fun buildBearerToken() = with(accountPreferencesReader) {
        "$tokenType $accessToken"
    }

}