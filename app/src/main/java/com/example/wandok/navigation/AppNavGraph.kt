package com.example.wandok.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.example.wandok.ui.home.search.SearchDetailScreen
import com.example.wandok.ui.home.search.SearchScreen
import com.example.wandok.ui.home.search.SearchViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
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
            showReadingProgress = {
                navController.navigate(LeafScreen.ReadingProgress.route)
            }
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
                navController.navigate(LeafScreen.SearchDetail.route+"/${it}")
            }
        )
    }
}

private fun NavGraphBuilder.showSearchDetail(navController: NavController) {
    composable(
        route = "${LeafScreen.SearchDetail.route}/{${NAV_ARGS_ISBN}}",
        arguments = listOf(navArgument(NAV_ARGS_ISBN) { type = NavType.StringType })
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(NAV_ARGS_ISBN)?.let {
            SearchDetailScreen(isbn = it)
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
