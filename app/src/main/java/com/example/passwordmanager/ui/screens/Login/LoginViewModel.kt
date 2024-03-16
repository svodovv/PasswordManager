package com.example.passwordmanager.ui.screens.Login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repositories.repository.PasswordRepositoryImpl
import com.omgupsapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: PasswordRepositoryImpl
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
        getPassword()
    }

    private fun getPassword() {
        viewModelScope.launch {
            repo.getMasterPassword().onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _loginState.value =
                            _loginState.value.copy(passBool = resource.data ?: false)
                    }

                    is Resource.Loading -> {
                        _loginState.value = _loginState.value.copy(onLoading = true)
                    }

                    is Resource.Error -> {
                        _loginState.value =
                            _loginState.value.copy(onError = resource.message ?: "Ошибка работы БД")
                    }
                }
            }
        }
    }
}