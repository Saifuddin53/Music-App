package com.myprojects.musicapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myprojects.musicapp.ui.AccountView
import com.myprojects.musicapp.ui.BrowseView
import com.myprojects.musicapp.ui.HomeView
import com.myprojects.musicapp.ui.Screen
import com.myprojects.musicapp.ui.SubscriptionView

@Composable
fun Navigation(
    navController: NavController,
    pd: PaddingValues
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }

        composable(Screen.DrawerScreen.Subscription.route) {
            SubscriptionView()
        }

        composable(Screen.BottomScreen.Home.route) {
            HomeView()
        }

        composable(Screen.BottomScreen.Library.route) {

        }

        composable(Screen.BottomScreen.Browse.route) {
            BrowseView()
        }
    }
}