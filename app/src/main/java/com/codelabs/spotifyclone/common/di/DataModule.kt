package com.codelabs.spotifyclone.common.di

import com.codelabs.spotifyclone.authorization.data.AccountLocalDataSourceImpl
import com.codelabs.spotifyclone.authorization.data.AccountRemoteDataSourceImpl
import com.codelabs.spotifyclone.authorization.data.AccountRepositoryImpl
import com.codelabs.spotifyclone.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.common.data.DataExceptionHandlerImpl
import com.codelabs.spotifyclone.common.data.api.SpotifyAccountService
import com.codelabs.spotifyclone.common.data.api.SpotifyService
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
import com.codelabs.spotifyclone.common.domain.ExceptionHandler
import com.codelabs.spotifyclone.playlist.data.PlaylistRemoteDataSourceImpl
import com.codelabs.spotifyclone.playlist.data.PlaylistRepositoryImpl
import com.codelabs.spotifyclone.playlist.domain.PlaylistRemoteDataSource
import com.codelabs.spotifyclone.playlist.domain.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

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