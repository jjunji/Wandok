package com.example.wandok.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.wandok.ui.EmptyScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Home.route
    ) {
        addHomeRoute(navController = navController)
    }
}

private fun NavGraphBuilder.addHomeRoute(navController: NavController) {
    navigation(
        startDestination = RootScreen.Home.route
    ) {
        showHome(navController)
    }
}

private fun NavGraphBuilder.showHome(navController: NavController) {
    composable(route = RootScreen.Home.route) {
        EmptyScreen()
    }
}
