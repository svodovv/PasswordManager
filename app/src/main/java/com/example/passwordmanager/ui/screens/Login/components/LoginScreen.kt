package com.example.passwordmanager.ui.screens.Login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.Screen
import com.example.passwordmanager.ui.screens.Login.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val loginState = loginViewModel.loginState.value
    val userAuth = remember {
        mutableStateOf<Boolean?>(null)
    }
    val passLength = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.firsLogintext),
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = loginState.firsPass,
            onValueChange = {
                loginViewModel.onChangeFirsLogin(it)
                userAuth.value = null
                passLength.value = false
                loginViewModel.changeUserAuth()
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (userAuth.value == false || passLength.value) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = if (userAuth.value == false || passLength.value) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.outline,
            )
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            if (passLength.value) {
                Text(
                    text = stringResource(R.string.ErrorInPassLenght),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
            if (loginState.userAuthenticated == false){
                Text(
                    text = stringResource(R.string.PasswordError),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Button(
            onClick = {
                if (loginState.firsPass.length <= 3 ) {
                    passLength.value = true
                } else {
                    loginViewModel.userAuthenticated(loginState.firsPass)
                }
            },  modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
        ) {
            Text(text = stringResource(R.string.SignIn))
        }
        LaunchedEffect(loginState.userAuthenticated) {
            if (loginState.userAuthenticated == true) {
                navController.navigate(Screen.IconListScreen.route)
            }
        }
    }
}