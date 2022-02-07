package com.codelabs.spotifyclone.authorization.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.codelabs.spotifyclone.MainActivity
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.presenter.UiState
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
import com.codelabs.spotifyclone.databinding.ActivityAuthorizationBinding
import com.google.android.material.snackbar.Snackbar
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity() {
    @Inject
    lateinit var authorizationRequest: AuthorizationRequest

    @Inject
    lateinit var accountPreferences: AccountPreferences.Reader

    private lateinit var binding: ActivityAuthorizationBinding
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequestAccess.setOnClickListener {
            AuthorizationClient.openLoginActivity(this, requestCode, authorizationRequest)
        }

        viewModel.stateFlow.asLiveData().observe(this) { state ->
            when (state) {
                is UiState.Loading -> setLoadingIndicator()
                is UiState.Success -> moveToHome()
                is UiState.Error -> showError(state.message)
                else -> {}
            }
        }

        if (accountPreferences.refreshToken != null) {
            moveToHome()
        }
    }

    private fun setLoadingIndicator() {
        binding.btnRequestAccess.apply {
            setText(R.string.loading)
            isEnabled = false
        }
    }

    private fun showError(message: String? = null) {
        binding.btnRequestAccess.apply {
            setText(R.string.request_access)
            isEnabled = true
        }

        Snackbar.make(
            findViewById(android.R.id.content),
            message ?: getString(R.string.unknown_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun moveToHome() {
        Intent(this, MainActivity::class.java).also {
            finish()
            startActivity(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Companion.requestCode) {
            val response = AuthorizationClient.getResponse(resultCode, data)

            when (response.type) {
                AuthorizationResponse.Type.CODE -> viewModel.getAccessToken(response.code)
                AuthorizationResponse.Type.ERROR -> showError(response.error)
                AuthorizationResponse.Type.EMPTY -> showError(response.error)
                else -> showError()
            }
        }
    }

    companion object {
        const val requestCode = 1
    }
}