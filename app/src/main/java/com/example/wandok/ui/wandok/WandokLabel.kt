package com.example.wandok.ui.wandok

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.data.model.BookDetail
import com.example.wandok.ui.core.BodyLargeText
import com.example.wandok.ui.core.BodyMediumText
import com.example.wandok.ui.core.H6Text
import com.example.wandok.ui.core.backGroundWithGradient
import com.example.wandok.ui.theme.Gray3D
import com.example.wandok.ui.theme.Gray8B
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange300
import com.example.wandok.ui.theme.Orange500
import com.example.wandok.ui.theme.WhiteGray
import com.example.wandok.ui.wandok.state.LabelState

@Composable
fun Label(labelState: LabelState<BookDetail>, countOfWandok: Int) {
    when (labelState) {
        is LabelState.None -> {

        }

        is LabelState.Selected -> {
            val data = labelState.bookDetail

            Column(
                modifier = Modifier
                    .width(312.dp)
                    .wrapContentHeight()
                    .background(color = Color.White, shape = shape)
            ) {
                NicknameLabel(countOfWandok)
                ContentDivider()
                WandokInfo(data.title, data.author)
                WandokDate()
                WandokFooter()
            }
        }
    }
}

// 담은 책 6권 완독!
@Composable
fun NicknameLabel(countOfWandok: Int) {
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
                    modifier = Modifier.wrapContentWidth(),
                    text = stringResource(
                        id = R.string.format_count_mybook,
                        formatArgs = arrayOf(countOfWandok)
                    )
                )
            }
            H6Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 2.dp),
                text = stringResource(id = R.string.common_wandok)
            )
        }
    }
}

@Composable
fun WandokInfo(title: String, author: String) {
    Column(
        modifier = Modifier.padding(start = 20.dp)
    ) {
        BodyLargeText(
            text = title, maxLines = 2, color = Gray3D
        )
        BodyMediumText(text = author, color = Gray3D)
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

// 시작일, 종료일
@Composable
fun WandokDate() {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 17.dp)
    ) {
        BodyMediumText(text = stringResource(id = R.string.common_target_start_date), color = Gray8B)
        BodyLargeText(modifier = Modifier.padding(top = 3.dp), text = "2024. 06. 20", color = Gray3D)
        BodyMediumText(modifier = Modifier.padding(top = 13.dp), text = stringResource(id = R.string.common_target_end_date), color = Gray8B)
        BodyLargeText(modifier = Modifier.padding(top = 3.dp), text = "2024. 06. 20", color = Gray3D)
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
        BodyLargeText(
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
            BodyMediumText(
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
            BodyMediumText(
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
            BodyMediumText(
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

@Preview(showBackground = true)
@Composable
fun PreviewLabel() {
    val labelState = LabelState.Selected(BookDetail("0000"))
    Label(labelState, 5)
}