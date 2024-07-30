package com.example.wandok.core

import kotlinx.serialization.Serializable

sealed interface MainTabRoute {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object WandokList : MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}