package com.example.wandok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wandok.navigation.AppNavGraph
import com.example.wandok.navigation.RootScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavRoutes = listOf(
        RootScreen.Home,
        RootScreen.Add,
        RootScreen.WandokList,
        RootScreen.MyPage
    )

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = bottomNavRoutes
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavHostController,
    items: List<RootScreen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier.clip(MaterialTheme.shapes.medium),
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
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}