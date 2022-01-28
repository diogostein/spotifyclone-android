package com.codelabs.spotifyclone.common.data.api

import com.codelabs.spotifyclone.common.Constants
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.HttpURLConnection

class TokenRefreshAuthenticator(
    private val spotifyAccountService: SpotifyAccountService,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // Make a synchronous request to get a new access token
//            val responseToken = spotifyAccountService.getRefreshedTokenSync(
//                "refresh_token",
//                "AuthPrefs.refreshToken",
//                Constants.SpotifyApi.clientId,
//                Constants.SpotifyApi.clientSecret
//            ).execute()
//
//            // Update prefs with new response data and continue with original request
//            if (responseToken.isSuccessful) {
//                val tokenResponse = responseToken.body()
////                tokenResponse?.apply {
////                    updatePrefs()
////                    Rockifly.login(accessToken)
////                }
//                return response.request
//                    .newBuilder()
//                    .header("Authorization", "AuthPrefs.tokenType AuthPrefs.accessToken")
//                    .build()
//            }
        }

        return null
    }

}