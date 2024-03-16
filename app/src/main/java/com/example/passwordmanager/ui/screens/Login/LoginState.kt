package com.example.passwordmanager.ui.screens.Login

import com.example.passwordmanager.data.Room.Model.UserAuthenticated

data class LoginState(
    val firsPass: String = "",
    val secondPass: String = "",
    val passBool: Boolean? = null, // Есть ли пароль
    val onLoading: Boolean = false,
    val onError: String = "",
    val userAuthenticated: Boolean? = null // Аутентифицирован ли пользователь
)
