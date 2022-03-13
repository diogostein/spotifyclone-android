package com.codelabs.spotifyclone.core.di

import com.codelabs.spotifyclone.features.authorization.data.AccountLocalDataSourceImpl
import com.codelabs.spotifyclone.features.authorization.data.AccountRemoteDataSourceImpl
import com.codelabs.spotifyclone.features.authorization.data.AccountRepositoryImpl
import com.codelabs.spotifyclone.features.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.features.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.features.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.core.data.DataExceptionHandlerImpl
import com.codelabs.spotifyclone.core.data.api.SpotifyAccountService
import com.codelabs.spotifyclone.core.data.api.SpotifyService
import com.codelabs.spotifyclone.core.data.preferences.AccountPreferences
import com.codelabs.spotifyclone.core.domain.ExceptionHandler
import com.codelabs.spotifyclone.features.playlist.data.PlaylistRemoteDataSourceImpl
import com.codelabs.spotifyclone.features.playlist.data.PlaylistRepositoryImpl
import com.codelabs.spotifyclone.features.playlist.domain.PlaylistRemoteDataSource
import com.codelabs.spotifyclone.features.playlist.domain.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun provideDataExceptionHandler(): ExceptionHandler {
        return DataExceptionHandlerImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideAccountRemoteDataSource(
        spotifyAccountService: SpotifyAccountService
    ): AccountRemoteDataSource {
        return AccountRemoteDataSourceImpl(spotifyAccountService)
    }

    @Provides
    @ViewModelScoped
    fun provideAccountLocalDataSource(
        accountPreferencesEditor: AccountPreferences.Editor
    ): AccountLocalDataSource {
        return AccountLocalDataSourceImpl(accountPreferencesEditor)
    }

    @Provides
    @ViewModelScoped
    fun provideAccountRepository(
        remoteDataSource: AccountRemoteDataSource,
        localDataSource: AccountLocalDataSource,
        exceptionHandler: ExceptionHandler
    ): AccountRepository {
        return AccountRepositoryImpl(remoteDataSource, localDataSource, exceptionHandler)
    }

    @Provides
    @ViewModelScoped
    fun providePlaylistRemoteDataSource(
        spotifyService: SpotifyService
    ): PlaylistRemoteDataSource {
        return PlaylistRemoteDataSourceImpl(spotifyService)
    }

    @Provides
    @ViewModelScoped
    fun providePlaylistRepository(
        remoteDataSource: PlaylistRemoteDataSource,
        exceptionHandler: ExceptionHandler
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(remoteDataSource, exceptionHandler)
    }

}