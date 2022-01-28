package com.codelabs.spotifyclone.common.di

import com.codelabs.spotifyclone.authorization.data.AccountLocalDataSourceImpl
import com.codelabs.spotifyclone.authorization.data.AccountRemoteDataSourceImpl
import com.codelabs.spotifyclone.authorization.data.AccountRepositoryImpl
import com.codelabs.spotifyclone.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRemoteDataSource
import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.common.data.api.SpotifyAccountService
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
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
    ): AccountRepository {
        return AccountRepositoryImpl(remoteDataSource, localDataSource)
    }

}