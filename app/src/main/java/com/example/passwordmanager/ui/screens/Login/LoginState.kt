package com.example.passwordmanager.ui.screens.Login


data class LoginState(
    val firsPass: String = "",
    val secondPass: String = "",
    val passBool: Boolean? = null, // Есть ли пароль
    val onLoading: Boolean = false,
    val onError: String = "",
    val userAuthenticated: Boolean? = null, // Аутентифицирован ли пользователь
    val userBiometricAnnotation: Boolean? = null, //Использовать ли аутентификацию по пальцу
    val biometricAuthDone: Boolean = false, // Авторизировался ли пользователь по отпечатку пальца
)
