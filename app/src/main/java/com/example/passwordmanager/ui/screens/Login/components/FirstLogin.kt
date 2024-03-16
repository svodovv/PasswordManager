package com.example.passwordmanager.ui.screens.Login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.theme.PasswordManagerTheme

@Composable
fun FirsLogin(){
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Введите мастер-пароль",
            modifier = Modifier.padding(all = 16.dp)
        )
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(
            onClick = {

            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Сохранить")
        }
    }
}

@Preview
@Composable
fun FirstLoginPreview(){
    PasswordManagerTheme {
        FirsLogin()
    }
}