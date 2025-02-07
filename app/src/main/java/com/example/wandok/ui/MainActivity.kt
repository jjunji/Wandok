package com.example.wandok.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wandok.ui.main.component.MainNavigator
import com.example.wandok.ui.main.component.rememberMainNavigator
import com.example.wandok.ui.theme.WandokTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAnalytics = Firebase.analytics
        val osVersion = Build.VERSION.RELEASE
        Timber.tag("test").e("osVersion: $osVersion")
        firebaseAnalytics.setUserProperty("android_os_version", osVersion)

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            WandokTheme {
                MainScreen(navigator = navigator)
            }
        }
    }
}