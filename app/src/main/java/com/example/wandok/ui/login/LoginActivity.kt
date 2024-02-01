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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.R
import com.example.wandok.ui.core.EditText
import com.example.wandok.ui.theme.BackGround
import com.example.wandok.ui.theme.GrayC1
import com.example.wandok.ui.theme.Red50
import com.example.wandok.ui.theme.Typography
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
    ) {
        Column {
            Logo()
            LoginForm()
            AccountHelp()
        }
    }
}

@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .padding(32.dp, 32.dp, 0.dp, 0.dp)
            .size(100.dp)
            .clip(CircleShape)
            .border(
                width = 12.dp,
                color = Red50,
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
    Column(
        modifier = Modifier.padding(top = 52.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            style = Typography.subtitle2,
            text = stringResource(id = R.string.common_id),
            color = GrayC1,
            fontSize = 14.sp
        )

        val id = remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .border(width = 2.dp, color = GrayC1, shape = RoundedCornerShape(10.dp))
        ) {
            EditText(
                text = id.value,
                onValueChange = { id.value = it },
                hint = stringResource(id = R.string.common_id)
            )
        }

        Text(
            modifier = Modifier.padding(top = 10.dp),
            style = Typography.subtitle2,
            text = stringResource(id = R.string.common_pwd),
            color = GrayC1,
            fontSize = 14.sp
        )

        val pwd = remember { mutableStateOf("") }
        Box(
            modifier = Modifier
                .border(width = 2.dp, color = GrayC1, shape = RoundedCornerShape(10.dp))
        ) {
            EditText(
                text = pwd.value,
                onValueChange = { pwd.value = it },
                hint = stringResource(id = R.string.common_pwd)
            )
        }
    }
}

@Composable
fun LoginOption() {

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
                text = stringResource(id = R.string.common_find_id)
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