package com.example.wandok.navigation

import androidx.annotation.DrawableRes
import com.example.wandok.R

sealed class RootScreen(
    val route: String,
    @DrawableRes val icon: Int,
    val title: String
) {
    object Home: RootScreen("home_root", R.drawable.ic_menu_home, "홈")
    object Add: RootScreen("add_root", R.drawable.ic_menu_add_book, "책 추가")
    object WandokList: RootScreen("wandok_list_root", R.drawable.ic_menu_wandok_list, "완독")
    object MyPage: RootScreen("my_page_root", R.drawable.ic_menu_my_page, "마이페이지")
}