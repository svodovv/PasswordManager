package com.example.passwordmanager.ui.screens.IconItemAdd.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.ui.Screen
import com.example.passwordmanager.ui.screens.IconItemAdd.IconItemAddViewModel

@Composable
fun IconItemAdd(
    navController: NavController, viewModel: IconItemAddViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(70.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = viewModel.siteInfo.value.url,
                onValueChange = {
                    viewModel.onChangeUrl(it)
                },
                label = { Text(text = "URL на картинку") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = viewModel.siteInfo.value.name,
                onValueChange = {
                    viewModel.onChangeSiteName(it)
                },
                label = { Text(text = "Наименование сайта") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = viewModel.siteInfo.value.password,
                onValueChange = {
                    viewModel.onChangePassword(it)
                },
                label = { Text(text = "Пароль") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                )
            )
            Button(onClick = {

                viewModel.saveNewSiteSettings()
                navController.navigate(Screen.IconListScreen.route)

            }, modifier = Modifier.width(200.dp)) {
                Text(text = "Сохранить ")
            }
        }
    }
}