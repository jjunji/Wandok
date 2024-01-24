package com.example.wandok.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.R
import com.example.wandok.ui.theme.WandokTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WandokTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Spacer(modifier = Modifier.padding(32.dp, 32.dp, 0.dp, 0.dp))
            Logo()
            AccountHelp()
        }
    }
}

@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(
                width = 12.dp,
                color = Color.White,
                shape = CircleShape
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_theme_book),
            contentDescription = ""
        )
    }
}

@Composable
fun LoginForm() {

}

@Composable
fun LoginButton() {

}

@Preview
@Composable
fun AccountHelp() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = stringResource(id = R.string.common_find_id),
            )

            Spacer(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(color = Color.Gray)
            )

            Text(
                text = stringResource(id = R.string.common_find_pwd)
            )
        }
    }
}