package com.example.passwordmanager.ui.screens.Login

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repositories.repository.BiometricRepositoryImpl
import com.example.passwordmanager.data.repositories.repository.PasswordRepositoryImpl
import com.example.passwordmanager.data.repositories.repository.UserAuthenticatedImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repoUserPass: PasswordRepositoryImpl,
    private val repoUserAuth: UserAuthenticatedImpl,
    private val repoBiometricAuth: BiometricRepositoryImpl
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
        viewModelScope.launch {
            userAuth()
            checkUserPhone()
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

    private fun checkUserPhone() {
        val checkUserPhone = repoBiometricAuth.authenticateUser()
        _loginState.value = _loginState.value.copy(userBiometricAnnotation = checkUserPhone)
    }

    private fun onlyBiometricAuth() {
        _loginState.value = _loginState.value.copy(userAuthenticated = true)
    }

    private fun biometricAuthOff() {
        _loginState.value = _loginState.value.copy(userBiometricAnnotation = false)
    }

    private val authCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            onlyBiometricAuth()

        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            biometricAuthOff()
            Log.e("BiometricAuth", "Error")
        }

        override fun onAuthenticationFailed() {
            biometricAuthOff()
            Log.e("BiometricAuth", "Failed")
        }
    }




    fun biometricPromptAuth(activity: FragmentActivity) {
        if (!_loginState.value.biometricAuthDone) {
            val promptInfo = repoBiometricAuth.promptInfo
            repoBiometricAuth.getBiometricPrompt(
                authCallback = authCallback,
                activity = activity
            ).authenticate(promptInfo)
            // Установите флаг, указывающий, что биометрическая аутентификация выполнена
            _loginState.value = _loginState.value.copy(biometricAuthDone = true)
        }
    }

}