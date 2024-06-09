package com.example.wandok.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.navigation.compose.rememberNavController
import com.example.wandok.navigation.MyNavHost
import com.example.wandok.navigation.RootScreen
import com.example.wandok.navigation.navigateToRootScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavRoutes = listOf(
        RootScreen.Home,
        RootScreen.Search,
        RootScreen.WandokList,
        RootScreen.MyPage
    )

    Scaffold(
        bottomBar = {
            MyBottomNavigation(
                navController = navController,
                items = bottomNavRoutes
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MyNavHost(navController = navController, innerPadding)
    }
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
            .clip(MaterialTheme.shapes.medium),
//        modifier = Modifier.clip(MaterialTheme.shapes.medium),
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
                    navController.navigateToRootScreen(item)
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