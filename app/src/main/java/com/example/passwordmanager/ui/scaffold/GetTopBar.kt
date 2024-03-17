package com.omgupsapp.presentation.scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.passwordmanager.ui.Screen

@Composable
fun GetTopBar(
    navController: NavController,
    route: String,
) {
    if (
        route == Screen.FirstLoginScreen.route ||
        route == Screen.LoginScreen.route ) {
        //
    } else {
        TopAppBarComposable(
            navController = navController,
            route = route,
        )
    }
}