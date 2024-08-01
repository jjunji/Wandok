package com.example.wandok.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries,
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}