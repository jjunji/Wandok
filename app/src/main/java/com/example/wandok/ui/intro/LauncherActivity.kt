package com.example.wandok.ui.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.wandok.R
import com.example.wandok.common.extension.IntentExt.navigateActivity
import com.example.wandok.ui.MainActivity
import com.example.wandok.ui.theme.Red100
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LauncherScreen()
        }

        val viewModel: LauncherViewModel by viewModels()
        lifecycleScope.launch {
            launch {
                viewModel.navigateToLogin.collect {
                    finishAffinity()
                    navigateActivity(MainActivity::class.java)
                }
            }

            launch {
                viewModel.navigateToMain.collect {
                    finishAffinity()
                    navigateActivity(MainActivity::class.java)
                }
            }
        }
    }
}

@Preview
@Composable
fun LauncherScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Red100
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = "logo"
        )
    }
}