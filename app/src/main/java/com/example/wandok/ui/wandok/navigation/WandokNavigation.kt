package com.example.wandok.ui.wandok.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.wandok.core.MainTabRoute

fun NavController.navigateWandok(navOptions: NavOptions) {
    navigate(MainTabRoute.WandokList, navOptions)
}

fun NavGraphBuilder.wandokListNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.WandokList> {
        WandokRoute(padding)
    }
}