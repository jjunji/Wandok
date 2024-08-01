package com.example.wandok.ui.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    // TODO: Box Wrap 제거해보기
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            homeNavGraph(
                padding = padding
            )
            searchNavGraph(
                padding = padding,
                onItemClicked = { navigator.navigateToSearchDetail(it) }
            )
            wandokListNavGraph(
                padding = padding
            )
            myPageNavGraph(
                padding = padding
            )
        }
    }
}