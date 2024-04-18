package com.myprojects.musicapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myprojects.musicapp.ui.MainView
import com.myprojects.musicapp.ui.Screen

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    NavHost(navController = navController, startDestination = Screen.DrawerScreen.Account.route) {
        composable(Screen.DrawerScreen.Account.dRoute) {
            MainView()
        }

        composable(Screen.DrawerScreen.AddAccount.dRoute) {

        }

        composable(Screen.DrawerScreen.Subscription.dRoute) {

        }
    }
}