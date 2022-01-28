package com.codelabs.spotifyclone.authorization.data

import com.codelabs.spotifyclone.authorization.domain.AccountLocalDataSource
import com.codelabs.spotifyclone.common.Resource
import com.codelabs.spotifyclone.common.data.model.Token
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences

class AccountLocalDataSourceImpl(
    private val editorPreferences: AccountPreferences.Editor,
) : AccountLocalDataSource {

    override fun saveCredentials(token: Token): Resource<Nothing> {
        editorPreferences.save(token)

        return Resource.Success()
    }

    override fun clearCredentials(): Resource<Nothing> {
        editorPreferences.clear()

        return Resource.Success()
    }

}