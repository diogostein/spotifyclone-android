package com.codelabs.spotifyclone.authorization.domain

import com.codelabs.spotifyclone.common.presentation.UiState
import com.codelabs.spotifyclone.common.domain.model.Token

interface AccountLocalDataSource {
    fun saveCredentials(token: Token): UiState<Nothing>
    fun clearCredentials(): UiState<Nothing>
}