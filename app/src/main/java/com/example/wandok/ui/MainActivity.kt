package com.example.wandok.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wandok.ui.MainScreen
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