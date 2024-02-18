package com.example.wandok.ui.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wandok.R
import com.example.wandok.ui.core.EditText
import com.example.wandok.ui.core.grayRoundCorner
import com.example.wandok.ui.theme.BackGround
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.GrayC1
import com.example.wandok.ui.theme.Orange
import com.example.wandok.ui.theme.Red50
import com.example.wandok.ui.theme.Typography
import com.example.wandok.ui.theme.WandokTheme
import com.example.wandok.ui.theme.WhiteOrange
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.math.log

@AndroidEntryPoint
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
fun LoginScreen(
    viewModel: LoginViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
    ) {
        val id by viewModel.id.collectAsState()
        val pwd by viewModel.password.collectAsState()
        val idClearVisible by viewModel.idClearVisible.collectAsState(initial = false)
        val pwdClearVisible by viewModel.pwdClearVisible.collectAsState(initial = false)
        val loginEnableState by viewModel.loginEnable.collectAsState(initial = false)

        Column {
            Logo()
            LoginForm(
                id,
                pwd,
                onIdChanged = { viewModel.onIdChanged(it) },
                onPwdChanged = { viewModel.onPasswordChanged(it) },
                onIdFocused = { viewModel.onIdFocusChanged(it) },
                onPwdFocused = { viewModel.onPwdFocusChanged(it) },
                idClearVisible = idClearVisible,
                pwdClearVisible = pwdClearVisible,
                idClear = { viewModel.clearId() },
                pwdClear = { viewModel.clearPassword() }
            )
            LoginOption()
            LoginButton(loginEnableState) { viewModel.login() }
            AccountHelp()
        }
    }
}

@Preview
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
fun LoginForm(
    id: String,
    password: String,
    onIdChanged: (String) -> Unit,
    onIdFocused: (Boolean) -> Unit,
    onPwdChanged: (String) -> Unit,
    onPwdFocused: (Boolean) -> Unit,
    idClearVisible: Boolean,
    pwdClearVisible: Boolean,
    idClear: () -> Unit,
    pwdClear: () -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 52.dp, bottom = 30.dp, end = 20.dp)
    ) {
        Text(
            style = Typography.subtitle2,
            text = stringResource(id = R.string.common_id),
            color = GrayC1,
            fontSize = 14.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 5.dp)
                .grayRoundCorner(),
            verticalAlignment = CenterVertically
        ) {
            EditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 10.dp)
                    .focusRequester(FocusRequester())
                    .onFocusChanged { onIdFocused(it.isFocused) },
                text = id,
                textSize = 14.sp,
                onValueChange = {
                    onIdChanged(it)
                },
                hint = stringResource(id = R.string.common_id)
            )

            if (idClearVisible) {
                Image(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 10.dp)
                        .clickable {
                            idClear()
                        },
                    painter = painterResource(id = R.drawable.ic_circle_close),
                    contentDescription = ""
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 10.dp),
            style = Typography.subtitle2,
            text = stringResource(id = R.string.common_pwd),
            color = GrayC1,
            fontSize = 14.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 5.dp)
                .grayRoundCorner(),
            verticalAlignment = CenterVertically
        ) {
            EditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 10.dp)
                    .focusRequester(FocusRequester())
                    .onFocusChanged { onPwdFocused(it.isFocused) },
                text = password,
                textSize = 14.sp,
                onValueChange = {
                    onPwdChanged(it)
                },
                hint = stringResource(id = R.string.common_pwd)
            )

            if (pwdClearVisible) {
                Image(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 10.dp)
                        .clickable {
                            pwdClear()
                        },
                    painter = painterResource(id = R.drawable.ic_circle_close),
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginOption() {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 20.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_checkbox_on), contentDescription = "")
        Text(text = stringResource(id = R.string.common_save_id))
        Image(
            modifier = Modifier.padding(start = 30.dp),
            painter = painterResource(id = R.drawable.ic_checkbox_off), contentDescription = ""
        )
        Text(text = stringResource(id = R.string.common_auto_login))
    }
}

@Composable
fun LoginButton(
    loginEnable: Boolean,
    login: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .height(52.dp),
        onClick = { login() },
        enabled = loginEnable,
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = if (loginEnable) Orange else WhiteOrange
        )
    ) {
        Text(
            text = stringResource(id = R.string.common_login),
            color = Color.White,
            fontWeight = Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountHelp() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = stringResource(id = R.string.common_find_id),
                fontSize = 14.sp,
                color = DarkGray
            )

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                    .width(1.dp)
                    .background(color = DarkGray)
            )

            Text(
                text = stringResource(id = R.string.common_find_pwd),
                fontSize = 14.sp,
                color = DarkGray
            )
        }
    }
}