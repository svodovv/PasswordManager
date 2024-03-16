package com.omgupsapp.presentation.scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun GetNavigationBar(
    route: String,
    navController: NavHostController,
) {
    if (true) {
        //Ничего)
    } else {
        NavigationBarComposable(
            navController = navController,
            route = route,
        )
    }
}