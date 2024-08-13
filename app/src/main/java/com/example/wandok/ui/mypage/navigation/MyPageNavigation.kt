package com.example.wandok.ui.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.wandok.core.MainTabRoute

fun NavController.navigateMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.MyPage> {
        MyPageRoute(paddingValues = padding)
    }
}