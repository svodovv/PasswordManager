package com.omgupsapp.presentation.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun ScaffoldComposable(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            GetTopBar(
                navController = navController,
                route = navBackStackEntry?.destination?.route.toString(),
            )}
    ) { paddingValues ->
        content(paddingValues)
    }
}