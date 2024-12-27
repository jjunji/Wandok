package com.example.wandok.ui.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.compose.NavHost
import com.example.wandok.ui.home.navigation.homeNavGraph
import com.example.wandok.ui.mypage.navigation.myPageNavGraph
import com.example.wandok.ui.search.navigation.searchNavGraph
import com.example.wandok.ui.wandok.navigation.wandokListNavGraph

/**
 * NavHost 를 통해 탐색 경로 정의
 */
@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    NavHost(
        modifier = modifier.background(White),
        navController = navigator.navController,
        startDestination = navigator.startDestination
    ) {
        homeNavGraph(
            padding = padding,
            onItemClicked = { navigator.navigateToHomeDetail(it) },
            onBackClicked = { navigator.popBackStackIfNotHome() }
        )
        searchNavGraph(
            padding = padding,
            onItemClicked = { isbn, isbn13 ->
                navigator.navigateToSearchDetail(isbn, isbn13)
            },
            onBackClicked = { navigator.popBackStackIfNotHome() },
            onAddCompleted = { navigator.navigateToRootScreen() }
        )
        wandokListNavGraph(
            padding = padding
        )
        myPageNavGraph(
            padding = padding
        )
    }

}