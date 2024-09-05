package com.example.wandok.core

import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object WandokList : MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}

sealed interface Route {
    @Serializable
    data class HomeDetail(val isbn: String) : Route

    @Serializable
    data class SearchDetail(val isbn: String) : Route
}