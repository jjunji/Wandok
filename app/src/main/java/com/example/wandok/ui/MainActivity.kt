package com.example.wandok.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wandok.ui.main.component.MainNavigator
import com.example.wandok.ui.main.component.rememberMainNavigator
import com.example.wandok.ui.theme.WandokTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            WandokTheme {
                MainScreen(navigator = navigator)
            }
        }
    }
}