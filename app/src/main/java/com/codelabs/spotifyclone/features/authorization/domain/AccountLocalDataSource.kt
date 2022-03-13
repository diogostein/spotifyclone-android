package com.codelabs.spotifyclone.features.authorization.domain

import com.codelabs.spotifyclone.core.presentation.UiState
import com.codelabs.spotifyclone.core.domain.model.Token

interface AccountLocalDataSource {
    fun saveCredentials(token: Token): UiState<Nothing>
    fun clearCredentials(): UiState<Nothing>
}