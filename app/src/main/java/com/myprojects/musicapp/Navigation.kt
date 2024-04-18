package com.myprojects.musicapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myprojects.musicapp.ui.MainView
import com.myprojects.musicapp.ui.Screen

@Composable
fun Navigation(
    navController: NavController,
    viewModel: MainViewModel = viewModel(),
    pd: PaddingValues
) {
    NavHost(navController = navController as NavHostController, startDestination = Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd)) {
        composable(Screen.DrawerScreen.Account.route) {

        }

        composable(Screen.DrawerScreen.Subscription.route) {

        }
    }
}