package com.omgupsapp.presentation.scaffold

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarComposable(
    route: String,
    navController: NavHostController,
) {

    NavigationBar {

    }
}

