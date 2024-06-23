package com.example.wandok.ui.wandok

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.ui.core.Body1Text
import com.example.wandok.ui.core.Body2Text
import com.example.wandok.ui.core.H6Text
import com.example.wandok.ui.core.backGroundWithGradient
import com.example.wandok.ui.core.shadowExcludeLeft
import com.example.wandok.ui.theme.Gray3D
import com.example.wandok.ui.theme.Gray8B
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange300
import com.example.wandok.ui.theme.Orange500
import com.example.wandok.ui.theme.WhiteGray

val cornerRadius = 16.dp

@Composable
fun WandokScreen() {
    val shape = RoundedCornerShape(topEnd = cornerRadius, bottomEnd = cornerRadius)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ShadowContainer {
            Column(
                modifier = Modifier
                    .width(312.dp)
                    .wrapContentHeight()
                    .background(color = Color.White, shape = shape)
            ) {
                NicknameLabel()
                ContentDivider()
                WandokInfo()
                WandokDate()
                WandokFooter()
            }
        }

        HorizontalWandokList()
    }
}

@Composable
fun ShadowContainer(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 50.dp)
            .wrapContentSize()
            .shadowExcludeLeft(
                borderRadius = cornerRadius, blurRadius = cornerRadius
            )
    ) {
        content()
    }
}

// 담은 책 6권 완독!
@Composable
fun NicknameLabel() {
    Column(
        modifier = Modifier.padding(top = 24.dp, start = 20.dp)
    ) {
        H6Text(text = "XXX 님은")

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
                    modifier = Modifier.wrapContentWidth(), text = "담은 책 6권"
                )
            }
            H6Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 2.dp), text = "완독!"
            )
        }
    }
}

@Composable
fun WandokInfo() {
    Column(
        modifier = Modifier.padding(start = 20.dp)
    ) {
        Body1Text(
            text = "나를 소모하지 않는 현명한 태도에 관하여", maxLines = 2, color = Gray3D
        )
        Body2Text(text = "전지훈", color = Gray3D)
    }
}

@Composable
fun ContentDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .height(1.dp)
            .background(WhiteGray)
    )
}

@Composable
fun WandokDate() {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 17.dp)
    ) {
        Body2Text(
            text = stringResource(id = R.string.common_start_date), color = Gray8B
        )

        Body1Text(
            modifier = Modifier.padding(top = 3.dp), text = "2024. 06. 20", color = Gray3D
        )

        Body2Text(
            modifier = Modifier.padding(top = 13.dp), text = stringResource(id = R.string.common_end_date), color = Gray8B
        )

        Body1Text(
            modifier = Modifier.padding(top = 3.dp), text = "2024. 06. 20", color = Gray3D
        )
    }
}

@Composable
fun WandokFooter() {
    val shape = RoundedCornerShape(bottomEnd = cornerRadius)
    Box(
        modifier = Modifier
            .padding(top = 25.dp)
            .height(54.dp)
            .fillMaxWidth()
            .backGroundWithGradient(shape = shape, Orange100, Orange500),
    ) {
        Body1Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp),
            color = Color.White,
            text = stringResource(id = R.string.common_wandok_date),
            bold = true
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 20.dp)
        ) {
            H6Text(
                modifier = Modifier.alignByBaseline(),
                color = Color.White,
                bold = true,
                text = "2024"
            )
            Body2Text(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 3.dp),
                color = Color.White,
                bold = true,
                text = stringResource(id = R.string.common_year)
            )
            H6Text(
                modifier = Modifier.alignByBaseline(),
                color = Color.White,
                bold = true,
                text = "06"
            )
            Body2Text(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 3.dp),
                color = Color.White,
                bold = true,
                text = stringResource(id = R.string.common_month)
            )
            H6Text(
                modifier = Modifier.alignByBaseline(),
                color = Color.White,
                bold = true,
                text = "20"
            )
            Body2Text(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 3.dp),
                color = Color.White,
                bold = true,
                text = stringResource(id = R.string.common_day)
            )
        }
    }

}

@Composable
fun HorizontalWandokList() {
    val itemsList = (1..20).toList() // 표시할 아이템 리스트

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterStart // 박스의 내용을 중앙 정렬
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                items = itemsList,
                key = { index, item ->
                    item
                }
            ) { index, item ->
                Body1Text(text = item.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNicknameLabel() {
    WandokScreen()
}