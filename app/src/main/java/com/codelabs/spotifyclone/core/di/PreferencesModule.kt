package com.codelabs.spotifyclone.core.di

import android.content.Context
import android.content.SharedPreferences
import com.codelabs.spotifyclone.core.data.preferences.AccountPreferences
import com.codelabs.spotifyclone.core.data.preferences.AccountPreferencesEditorImpl
import com.codelabs.spotifyclone.core.data.preferences.AccountPreferencesReaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun provideAccountSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("account_prefs", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAccountPreferencesEditor(sharedPreferences: SharedPreferences): AccountPreferences.Editor {
        return AccountPreferencesEditorImpl(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideAccountPreferencesReader(sharedPreferences: SharedPreferences): AccountPreferences.Reader {
        return AccountPreferencesReaderImpl(sharedPreferences)
    }

}