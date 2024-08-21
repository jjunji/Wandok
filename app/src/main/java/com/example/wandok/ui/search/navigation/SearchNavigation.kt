package com.example.wandok.ui.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.wandok.core.MainTabRoute
import com.example.wandok.core.Route
import com.example.wandok.ui.search.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(MainTabRoute.Search, navOptions)
}

fun NavController.navigateSearchDetail(isbn: String) {
    navigate(Route.SearchDetail(isbn))
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
    onItemClicked: (isbn: String) -> Unit,
    onBackClicked: () -> Unit,
    onAddCompleted: () -> Unit
) {
    composable<MainTabRoute.Search> {
        SearchRoute(padding, onItemClick = { onItemClicked(it) })
    }

    composable<Route.SearchDetail> { _ ->
//        val isbn = navBackStackEntry.toRoute<Route.SearchDetail>().isbn
//        SearchDetailRoute(
//            onBackClicked = { onBackClicked() },
//            onAddCompleted = { onAddCompleted() }
//        )
    }
}