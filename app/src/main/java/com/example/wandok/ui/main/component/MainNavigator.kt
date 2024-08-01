package com.example.wandok.ui.main.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.wandok.ui.home.navigation.navigateHome
import com.example.wandok.ui.main.MainTab
import com.example.wandok.ui.mypage.navigation.navigateMyPage
import com.example.wandok.ui.search.navigation.navigateSearch
import com.example.wandok.ui.search.navigation.navigateSearchDetail
import com.example.wandok.ui.wandok.navigation.navigateWandok

/**
 * 페이지 전환에 대한 명세와 navController 제어
 */
internal class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    // BottomNavigation 클릭 시 호출
    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> {
                navController.navigateHome(navOptions)
            }

            MainTab.SEARCH -> {
                navController.navigateSearch(navOptions)
            }

            MainTab.WANDOK_LIST -> {
                navController.navigateWandok(navOptions)
            }

            MainTab.MY_PAGE -> {
                navController.navigateMyPage(navOptions)
            }
        }
    }

    // 검색 상세 페이지
    fun navigateToSearchDetail(isbn: String) {
        navController.navigateSearchDetail(isbn)
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    // TODO: remember key 로 navController 를 사용하지 않았을 때
    MainNavigator(navController)
}