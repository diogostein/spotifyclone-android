package com.codelabs.spotifyclone.common.di

import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.authorization.domain.usecase.GetAccessToken
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
}