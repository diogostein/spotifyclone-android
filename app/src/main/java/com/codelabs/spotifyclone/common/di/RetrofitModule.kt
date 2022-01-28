package com.codelabs.spotifyclone.common.di

import com.codelabs.spotifyclone.common.data.api.AuthorizationInterceptor
import com.codelabs.spotifyclone.common.data.api.SpotifyAccountService
import com.codelabs.spotifyclone.common.data.api.SpotifyService
import com.codelabs.spotifyclone.common.data.api.TokenRefreshAuthenticator
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(
        accountPreferencesReader: AccountPreferences.Reader
    ) = AuthorizationInterceptor(accountPreferencesReader)

    @Singleton
    @Provides
    fun provideTokenServiceAuthenticator(
        spotifyAccountService: SpotifyAccountService
    ) = TokenRefreshAuthenticator(spotifyAccountService)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor,
        tokenRefreshAuthenticator: TokenRefreshAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .authenticator(tokenRefreshAuthenticator)
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideSpotifyService(okHttpClient: OkHttpClient): SpotifyService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyService::class.java)
    }

    @Singleton
    @Provides
    @Named("simpleOkHttpClient")
    fun provideSimpleOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    }

    @Singleton
    @Provides
    fun provideSpotifyAccountService(
        @Named("simpleOkHttpClient") okHttpClient: OkHttpClient
    ): SpotifyAccountService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://accounts.spotify.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyAccountService::class.java)
    }

}