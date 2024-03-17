package com.example.passwordmanager.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.ui.screens.IconItem.components.IconItem
import com.example.passwordmanager.ui.screens.IconItemAdd.components.IconItemAdd
import com.example.passwordmanager.ui.screens.IconItemEdit.components.IconItemEdit
import com.example.passwordmanager.ui.screens.IconList.components.IconList
import com.example.passwordmanager.ui.screens.Login.LoginViewModel
import com.example.passwordmanager.ui.screens.Login.components.FirsLogin
import com.example.passwordmanager.ui.screens.Login.components.LoginScreen
import com.omgupsapp.presentation.scaffold.ScaffoldComposable

@Composable
fun App(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val loginState = loginViewModel.loginState.value

    val startDestination = if (loginState.passBool == true) Screen.LoginScreen.route
    else Screen.FirstLoginScreen.route

    val navController = rememberNavController()

    var siteNameEdit: String? = null
    ScaffoldComposable(navController = navController) { paddingValues ->
        NavHost(navController = navController, startDestination = startDestination) {
            composable(route = Screen.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
            composable(route = Screen.FirstLoginScreen.route) {
                FirsLogin(navController = navController)
            }
            composable(route = Screen.IconListScreen.route) {
                IconList(navController = navController, paddingValues = paddingValues)
            }
            composable(route = Screen.IconItemScreen.route + "/{siteName}") { navBackStackEntry ->
                val siteName = navBackStackEntry.arguments?.getString("siteName")
                siteName?.let {
                    siteNameEdit = it
                    IconItem(iconName = siteName)
                }
            }
            composable(route = Screen.IconItemEditScreen.route) {
                siteNameEdit?.let {
                    IconItemEdit(name = it, navController = navController)
                }
            }
            composable(route = Screen.IconItemAddScreen.route) {

                IconItemAdd(navController = navController)

            }
        }
    }

}


sealed class Screen(val route: String) {
    object IconListScreen : Screen("iconList_screen")
    object IconItemScreen : Screen("iconItem_screen")
    object IconItemEditScreen : Screen("iconItemEdit_screen")
    object FirstLoginScreen : Screen("firstLogin_screen")
    object LoginScreen : Screen("login_screen")
    object IconItemAddScreen : Screen("iconItemAdd_screen")

}