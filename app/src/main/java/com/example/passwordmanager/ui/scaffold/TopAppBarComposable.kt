package com.omgupsapp.presentation.scaffold

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.R
import com.example.passwordmanager.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(
    navController: NavController,
    selectedTitle: String
    ) {
    TopAppBar(
        title = {
            Text(
                text = selectedTitle,
                style = MaterialTheme.typography.displayMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painterResource(id = R.drawable.sharp_arrow_back_30),
                    contentDescription = "Notifications button"
                )
            }
        },

        actions = {
            IconButton(onClick = {
                val siteName = navController.currentBackStackEntry?.destination?.parent?.route
                siteName?.let {
                    navController.navigate(
                        Screen.IconItemEditScreen.route + "/{siteName}/edit".replace(
                            oldValue = "{siteName}",
                            newValue = it
                        )
                    )
                    Log.e("NAVCONTROLLER", siteName)
                }
            }) {
                Icon(
                    painterResource(id = R.drawable.baseline_edit_30),
                    contentDescription = "Edit icon"
                )
            }

        }
    )
}

