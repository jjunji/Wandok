package com.example.wandok.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wandok.navigation.RootScreen
import com.example.wandok.ui.main.MainTab
import com.example.wandok.ui.main.component.MainBottomBar
import com.example.wandok.ui.main.component.MainNavHost
import com.example.wandok.ui.main.component.MainNavigator
import com.example.wandok.ui.main.component.rememberMainNavigator

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator()
) {
//    val navController = rememberNavController()
//
//    val bottomNavRoutes = listOf(
//        RootScreen.Home,
//        RootScreen.Search,
//        RootScreen.WandokList,
//        RootScreen.MyPage
//    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationBarsPadding(),
                visible = true, // TODO:
                tabs = MainTab.entries,
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}

@Composable
fun MyBottomNavigation(
    navController: NavHostController,
    items: List<RootScreen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .navigationBarsPadding()
            .clip(MaterialTheme.shapes.medium)
            .padding(),
        backgroundColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                label = { Text(item.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = ""
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
//                    navController.navigateToRootScreen(item)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}