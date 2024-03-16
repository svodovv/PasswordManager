package com.example.passwordmanager.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.ui.screens.IconItem.components.IconItem
import com.example.passwordmanager.ui.screens.IconItem.components.IconItemEdit
import com.example.passwordmanager.ui.screens.IconList.components.IconList
import com.omgupsapp.presentation.scaffold.ScaffoldComposable

@Composable
fun App(
) {

    val navController = rememberNavController()

    ScaffoldComposable(navController = navController) {
        NavHost(navController = navController, startDestination = Screen.IconListScreen.route) {
            composable(route = Screen.IconListScreen.route) {
                IconList(navController = navController)
            }
            composable(route = Screen.IconItemScreen.route + "/{siteName}") { navBackStackEntry ->
                val siteName = navBackStackEntry.arguments?.getString("siteName")
                siteName?.let {
                    IconItem(iconName = siteName)
                }
            }
            composable(route = Screen.IconItemEditScreen.route + "/{siteName}" + "/edit") { navBackStackEntry ->
                val siteName = navBackStackEntry.arguments?.getString("siteName")
                siteName?.let {
                    IconItemEdit(name = siteName)
                }
            }
        }
    }
}


sealed class Screen(val route: String) {
    object IconListScreen : Screen("iconList_screen")
    object IconItemScreen : Screen("iconItem_screen")
    object IconItemEditScreen : Screen("iconItemEdit_screen")

}