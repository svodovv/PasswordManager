package com.example.passwordmanager.ui.screens.Login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repositories.repository.PasswordRepositoryImpl
import com.example.passwordmanager.data.repositories.repository.UserAuthenticatedImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repoUserPass: PasswordRepositoryImpl,
    private val repoUserAuth: UserAuthenticatedImpl
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
        viewModelScope.launch {
            userAuth()
        }
    }

    private fun userAuth() {
        repoUserAuth.getUserAuth().observeForever {
            _loginState.value = _loginState.value.copy(passBool = it.userAuthenticated)

        }

    }


    fun onChangeFirsLogin(firsPass: String) {
        _loginState.value = _loginState.value.copy(firsPass = firsPass)
    }

    fun onChangeSecondLogin(secondPass: String) {
        _loginState.value = _loginState.value.copy(secondPass = secondPass)
    }

    fun passwordsEquals() {
        if (loginState.value.firsPass == loginState.value.secondPass) {
            viewModelScope.launch {
                repoUserAuth.userSavePass() // Сохраняет значение что пользователь уже имеет парль
                repoUserPass.insertPassword(loginState.value.firsPass) // Сохраняет пароль
            }
            _loginState.value = _loginState.value.copy(passBool = true)
        }
    }

    // Проверка на совпадение паролей
    fun userAuthenticated(password: String) {
        repoUserPass.userAuthenticated(password).observeForever {
            _loginState.value = _loginState.value.copy(userAuthenticated = it)
        }
    }

    fun changeUserAuth() {
        _loginState.value = _loginState.value.copy(userAuthenticated = null)
    }
}