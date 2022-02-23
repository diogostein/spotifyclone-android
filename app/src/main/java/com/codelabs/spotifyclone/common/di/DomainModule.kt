package com.codelabs.spotifyclone.common.di

import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.authorization.domain.usecase.GetAccessToken
import com.codelabs.spotifyclone.playlist.domain.PlaylistRepository
import com.codelabs.spotifyclone.playlist.domain.usecase.GetMyPlaylists
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideGetAccessTokenUseCase(accountRepository: AccountRepository): GetAccessToken {
        return GetAccessToken(accountRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMyPlaylistsUseCase(playlistRepository: PlaylistRepository): GetMyPlaylists {
        return GetMyPlaylists(playlistRepository)
    }
}