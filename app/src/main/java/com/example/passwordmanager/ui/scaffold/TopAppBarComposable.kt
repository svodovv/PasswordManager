package com.omgupsapp.presentation.scaffold

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(
    navController: NavController,
    route: String,
) {
    TopAppBar(
        title = {
        },

        navigationIcon = {
            if (route != Screen.IconListScreen.route) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painterResource(id = R.drawable.sharp_arrow_back_30),
                        contentDescription = "Notifications button"
                    )
                }
            }
        },


        actions = {
            if (route != Screen.IconItemEditScreen.route &&
                route != Screen.IconListScreen.route
                ) {
                IconButton(onClick = {
                    navController.navigate(Screen.IconItemEditScreen.route)
                }) {
                    Icon(
                        painterResource(id = R.drawable.baseline_edit_30),
                        contentDescription = "Edit icon"
                    )
                }
            }
            if (route == Screen.IconListScreen.route){
                IconButton(onClick = {
                    navController.navigate(Screen.IconItemAddScreen.route)
                }) {
                    Icon(
                        painterResource(id = R.drawable.round_add_circle_30),
                        contentDescription = "Add"
                    )
                }
            }
        }
    )
}

