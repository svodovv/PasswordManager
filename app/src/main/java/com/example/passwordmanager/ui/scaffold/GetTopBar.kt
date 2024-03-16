package com.omgupsapp.presentation.scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.passwordmanager.ui.Screen

@Composable
fun GetTopBar(
    navController: NavController,
    route: String,
    selectedTitle: String
) {
    if (route == Screen.IconListScreen.route ||
        route == Screen.FirstLoginScreen.route ||
        route == Screen.LoginScreen.route ||
        route == Screen.LoadingScreen.route) {
        //
    } else {
        TopAppBarComposable(
            navController = navController,
            selectedTitle = selectedTitle
        )
    }
}