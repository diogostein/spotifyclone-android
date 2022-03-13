package com.codelabs.spotifyclone.features.authorization.data

import com.codelabs.spotifyclone.features.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.core.domain.model.Token
import com.codelabs.spotifyclone.core.data.preferences.AccountPreferences

class AccountLocalDataSourceImpl(
    private val editorPreferences: AccountPreferences.Editor,
) : AccountLocalDataSource {

    override fun saveCredentials(token: Token): UiState<Nothing> {
        editorPreferences.save(token)

        return UiState.Success()
    }

    override fun clearCredentials(): UiState<Nothing> {
        editorPreferences.clear()

        return UiState.Success()
    }

}