package com.example.wandok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.wandok.common.constants.AppConstant.SPLASH_TIME
import com.example.wandok.common.extension.IntentExt.navigateActivity
import com.example.wandok.ui.MainActivity
import com.example.wandok.ui.login.LoginActivity
import com.example.wandok.ui.theme.Red100
import com.example.wandok.ui.theme.WandokTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LauncherScreen()
        }
        lifecycleScope.launch {
            delay(SPLASH_TIME)
            finishAffinity()
            navigateActivity(LoginActivity::class.java)
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