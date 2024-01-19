package com.example.wandok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wandok.ui.theme.WandokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WandokTheme {
                MainScreen()
            }
        }
    }
}

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController) }
//    ) {
//        Box(modifier = Modifier.padding(it)) {
//            NavigationGraph(navController = navController)
//        }
//    }
//}

//@Composable
//fun BottomNavigationBar(navController: NavHostController) {
//    val items = listOf(
//        BottomNavItem.Home,
//        BottomNavItem.Add,
//        BottomNavItem.WandokList,
//        BottomNavItem.MyPage
//    )
//
//    BottomNavigation(
//        backgroundColor = Color.White
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        items.forEach { item ->
//            BottomNavigationItem(
//                label = { Text(item.title) },
//                icon = {
//                    Icon(
//                        painter = painterResource(id = item.icon),
//                        contentDescription = ""
//                    )
//                },
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let {
//                            popUpTo(it) { saveState = true }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}

//@Composable
//fun NavigationGraph(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
//        composable(BottomNavItem.Home.route) {
//            EmptyScreen()
//        }
//        composable(BottomNavItem.Add.route) {
//            EmptyScreen()
//        }
//        composable(BottomNavItem.WandokList.route) {
//            EmptyScreen()
//        }
//        composable(BottomNavItem.MyPage.route) {
//            EmptyScreen()
//        }
//    }
//}





