package com.example.wandok.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.ui.core.Body1Text
import com.example.wandok.ui.core.Body2Text
import com.example.wandok.ui.core.H6Text
import com.example.wandok.ui.core.shadow
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.DivideLine
import com.example.wandok.ui.theme.LightShadow
import com.example.wandok.ui.theme.Orange300

val shadowColor = LightShadow
val CornerRadius = 10.dp

@Composable
fun MyPageScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Greeting()
        LoginInfoLabel()
        LoginInfo()
        AutoLoginSection()
        MyReadingTime()
        MyReadingTimeCard()
        DividerLine()
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier.padding(top = 24.dp, start = 20.dp)
    ) {
        Row {
            Box(modifier = Modifier.width(IntrinsicSize.Max)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .offset(y = (-4).dp)
                        .align(Alignment.BottomCenter)
                        .background(color = Orange300)
                )
                H6Text(
                    modifier = Modifier.wrapContentWidth(), text = "XXX 님"
                )
            }
            H6Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 2.dp), text = "안녕하세요!"
            )
        }
    }
}

@Composable
fun LoginInfoLabel() {
    Body2Text(
        text = stringResource(id = R.string.mypage_label_login_info),
        color = DarkGray,
        modifier = Modifier
            .padding(start = 16.dp, top = 22.dp)
    )
}

@Composable
fun LoginInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        H6Text(
            text = "account123",
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Body2Text(
                text = stringResource(id = R.string.common_logout),
                color = DarkGray,
                modifier = Modifier.padding(end = 6.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun AutoLoginSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        H6Text(
            text = stringResource(id = R.string.common_auto_login),
        )

        // TODO:
    }
}

@Composable
fun MyReadingTime(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
            )

            Body1Text(
                text = stringResource(id = R.string.mypage_label_read_time),
                modifier = Modifier.padding(start = 4.dp),
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            modifier = Modifier
//                .clickable(onClick = onChartClick)
                .padding(horizontal = 16.dp)
                .wrapContentSize()
        )
    }
}

@Composable
fun MyReadingTimeCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()

                .shadow(
                    shadowColor,
                    borderRadius = CornerRadius,
                    offsetX = 3.dp,
                    offsetY = 3.dp,
                    spread = 3.dp,
                    blurRadius = CornerRadius
                )
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.White)
                    .width(200.dp)
                    .height(112.dp)
            ) {

            }
        }

        Box(
            modifier = Modifier
                .padding(start = 11.dp)
                .wrapContentWidth()
                .shadow(
                    shadowColor,
                    borderRadius = CornerRadius,
                    offsetX = 7.dp,
                    offsetY = 7.dp,
                    spread = 7.dp,
                    blurRadius = CornerRadius
                )
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.White)
                    .width(117.dp)
                    .height(112.dp)
            ) {

            }
        }
    }
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(DivideLine)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    Greeting()
}

@Preview(showBackground = true)
@Composable
fun PreviewMyReadingTime() {
    MyReadingTime()
}

@Preview(showBackground = true)
@Composable
fun PreviewMyReadingTimeCard() {
    MyReadingTimeCard()
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginInfoLabel() {
    LoginInfoLabel()
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginInfo() {
    LoginInfo()
}