package com.codelabs.spotifyclone.authorization.data

import com.codelabs.spotifyclone.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.common.presentation.UiState
import com.codelabs.spotifyclone.common.domain.model.Token
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences

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