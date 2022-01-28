package com.codelabs.spotifyclone.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.spotifyclone.authorization.domain.AccountRepository
import com.codelabs.spotifyclone.common.Resource
import com.codelabs.spotifyclone.common.data.model.Authorization
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    private val _state = MutableLiveData<Resource<Nothing>>()
    val state: LiveData<Resource<Nothing>> = _state

    fun getAccessToken(authorization: Authorization) {
        _state.value = Resource.Loading

        viewModelScope.launch {
            repository.getAccessToken(authorization)

            _state.postValue(Resource.Success())
        }
    }

}