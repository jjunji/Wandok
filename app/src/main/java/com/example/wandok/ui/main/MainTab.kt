package com.example.wandok.ui.main

import androidx.compose.runtime.Composable
import com.example.wandok.R
import com.example.wandok.core.MainTabRoute

internal enum class MainTab(
    val iconResId: Int,
    val contentDescription: String,
    val route: MainTabRoute
) {
    HOME(
        iconResId = R.drawable.ic_menu_home,
        contentDescription = "홈",
        route = MainTabRoute.Home
    ),
    SEARCH(
        iconResId = R.drawable.ic_menu_add_book,
        contentDescription = "검색",
        route = MainTabRoute.Search
    ),
    WANDOK_LIST(
        iconResId = R.drawable.ic_menu_wandok_list,
        contentDescription = "완독 목록",
        route = MainTabRoute.WandokList
    ),
    MY_PAGE(
        iconResId = R.drawable.ic_menu_my_page,
        contentDescription = "마이 페이지",
        route = MainTabRoute.MyPage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

//        @Composable
//        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
//            return entries.map { it.route }.any { predicate(it) }
//        }
    }
}