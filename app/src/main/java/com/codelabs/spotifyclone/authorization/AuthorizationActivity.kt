package com.codelabs.spotifyclone.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.codelabs.spotifyclone.MainActivity
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.common.Constants
import com.codelabs.spotifyclone.common.Resource
import com.codelabs.spotifyclone.common.data.model.Authorization
import com.codelabs.spotifyclone.common.data.preferences.AccountPreferences
import com.codelabs.spotifyclone.databinding.ActivityAuthorizationBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity() {
    companion object {
        const val requestCode = 1
    }

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

        viewModel.state.observe(this) { state ->
            when (state) {
                is Resource.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                is Resource.Success -> moveToHome()
                is Resource.Error -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        if (accountPreferences.accessToken != null) {
            moveToHome()
        }
    }

    private fun moveToHome() {
        Intent(this, MainActivity::class.java).also {
            finish()
            startActivity(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AuthorizationActivity.requestCode) {
            val response = AuthorizationClient.getResponse(resultCode, data)

            when (response.type) {
                AuthorizationResponse.Type.CODE -> {
                    viewModel.getAccessToken(
                        Authorization(
                            code = response.code,
                            grantType = "authorization_code",
                            redirectUri = Constants.SpotifyApi.redirectUri
                        )
                    )
                }
                AuthorizationResponse.Type.ERROR -> {
                    Toast.makeText(this, "Error: ${response.error}", Toast.LENGTH_SHORT).show()
                }
                else -> Log.d("DS", "empty")
            }
        }
    }

}