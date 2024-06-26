package com.example.wandok.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.wandok.common.constants.KeyValueConstant.NAV_ARGS_ISBN
import com.example.wandok.ui.EmptyScreen
import com.example.wandok.ui.home.HomeScreen
import com.example.wandok.ui.home.ReadingProgressScreen
import com.example.wandok.ui.search.SearchDetailScreen
import com.example.wandok.ui.search.SearchScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = RootScreen.Home.route
    ) {
        addHomeRoute(navController = navController)
        addSearchRoute(navController = navController)
        addWandokList(navController = navController)
        addMyPage(navController = navController)
    }
}

private fun NavGraphBuilder.addHomeRoute(navController: NavController) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showHome(navController)
        showReadingProgress(navController)
    }
}

private fun NavGraphBuilder.showHome(navController: NavController) {
    composable(route = LeafScreen.Home.route) {
        HomeScreen(
//            showReadingProgress = {
//                navController.navigate(LeafScreen.ReadingProgress.route)
//            }
        )
    }
}

private fun NavGraphBuilder.showReadingProgress(navController: NavController) {
    composable(route = LeafScreen.ReadingProgress.route) {
        ReadingProgressScreen(
            onBack = {
                navController.navigateUp()
            }
        )
    }
}

private fun NavGraphBuilder.addSearchRoute(navController: NavController) {
    navigation(
        route = RootScreen.Search.route,
        startDestination = LeafScreen.Search.route
    ) {
        showSearch(navController)
        showSearchDetail(navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun NavGraphBuilder.showSearch(navController: NavController) {
    composable(route = LeafScreen.Search.route) {
        SearchScreen(
            navigateSearchDetailScreen = {
                navController.navigate(LeafScreen.SearchDetail.route + "/${it}")
            }
        )
    }
}

private fun NavGraphBuilder.showSearchDetail(navController: NavController) {
    composable(
        route = "${LeafScreen.SearchDetail.route}/{${NAV_ARGS_ISBN}}",  // isbn navigation parameter 로 전달
        arguments = listOf(navArgument(NAV_ARGS_ISBN) { type = NavType.StringType }),
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(NAV_ARGS_ISBN)?.let {
            SearchDetailScreen(
                onBackClicked = {
                    navController.navigateUp()
                },
                onAddCompleted = {
                    navController.navigateToRootScreen(RootScreen.Home)
                }
            )
        }
    }
}

private fun NavGraphBuilder.addWandokList(navController: NavController) {
    navigation(
        route = RootScreen.WandokList.route,
        startDestination = LeafScreen.WandokList.route
    ) {
        showWandokList(navController)
    }
}

private fun NavGraphBuilder.showWandokList(navController: NavController) {
    composable(route = LeafScreen.WandokList.route) {
        EmptyScreen()
    }
}

private fun NavGraphBuilder.addMyPage(navController: NavController) {
    navigation(
        route = RootScreen.MyPage.route,
        startDestination = LeafScreen.MyPage.route
    ) {
        showMyPage(navController)
    }
}

private fun NavGraphBuilder.showMyPage(navController: NavController) {
    composable(route = LeafScreen.MyPage.route) {
        EmptyScreen()
    }
}

fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
    navigate(rootScreen.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}