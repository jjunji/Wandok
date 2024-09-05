package com.example.wandok.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.wandok.core.MainTabRoute
import com.example.wandok.core.Route
import com.example.wandok.ui.home.HomeDetailRoute
import com.example.wandok.ui.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavController.navigateHomeDetail(isbn: String) {
    navigate(Route.HomeDetail(isbn))
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    onItemClicked: (isbn: String) -> Unit,
    onBackClicked: () -> Unit
) {
    composable<MainTabRoute.Home> {
        HomeRoute(
            padding,
            onItemClicked = { onItemClicked(it) }
        )
    }

    composable<Route.HomeDetail> {
        HomeDetailRoute(
            onBackClicked = { onBackClicked() }
        )
    }
}