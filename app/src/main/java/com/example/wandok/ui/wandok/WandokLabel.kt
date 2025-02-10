package com.example.wandok.ui.wandok

import android.content.Context
import android.graphics.drawable.BitmapDrawable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.wandok.R
import com.example.wandok.common.extension.toFormattedDate
import com.example.wandok.common.extension.toYearMonthDay
import com.example.wandok.data.model.BookDetail
import com.example.wandok.ui.core.BodyLargeText
import com.example.wandok.ui.core.BodyMediumText
import com.example.wandok.ui.core.H6Text
import com.example.wandok.ui.core.backGroundWithGradient
import com.example.wandok.ui.theme.Gray3D
import com.example.wandok.ui.theme.Gray8B
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange500
import com.example.wandok.ui.theme.WhiteGray
import com.example.wandok.ui.wandok.state.LabelState

val labelShape = RoundedCornerShape(topEnd = cornerRadius, bottomEnd = cornerRadius)
val footerShape = RoundedCornerShape(bottomEnd = cornerRadius)

@Composable
fun WandokLabel(labelState: LabelState<BookDetail>, countOfWandok: Int) {
    val context = LocalContext.current
    when (labelState) {
        is LabelState.None -> {
        }

        is LabelState.Selected -> {
            val data = labelState.bookDetail
            var startColor by remember { mutableStateOf(Orange100) }
            var endColor by remember { mutableStateOf(Orange500) }

            LaunchedEffect(data.image) {
                val colors = extractColorsFromUrl(context, data.image)
                startColor = colors.first
                endColor = colors.second
            }

            Column(
                modifier = Modifier
                    .width(312.dp)
                    .wrapContentHeight()
                    .background(color = Color.White, shape = labelShape)
            ) {
                NicknameLabel(countOfWandok, endColor)
                ContentDivider()
                WandokInfo(data.title, data.author)
                WandokDate(data.targetStartTimeMillis, data.targetEndTimeMillis)
                WandokFooter(data.wandokTimeMillis, startColor, endColor)
            }
        }
    }
}

// 담은 책 6권 완독!
@Composable
fun NicknameLabel(countOfWandok: Int, bottomLineColor: Color) {
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
                        .background(color = bottomLineColor)
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
        BodyLargeText(text = title, maxLines = 2, color = Gray3D, textAlign = TextAlign.Start)
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
fun WandokDate(
    targetStartTimeMillis: Long?,
    targetEndTimeMillis: Long?
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 17.dp)
    ) {
        val targetStartDate = targetStartTimeMillis?.toFormattedDate() ?: "-"
        val targetEndDate = targetEndTimeMillis?.toFormattedDate() ?: "-"

        BodyMediumText(text = stringResource(id = R.string.common_target_start_date), color = Gray8B)
        BodyLargeText(modifier = Modifier.padding(top = 3.dp), text = targetStartDate, color = Gray3D)
        BodyMediumText(modifier = Modifier.padding(top = 13.dp), text = stringResource(id = R.string.common_target_end_date), color = Gray8B)
        BodyLargeText(modifier = Modifier.padding(top = 3.dp), text = targetEndDate, color = Gray3D)
    }
}

@Composable
fun WandokFooter(
    wandokTimeMillis: Long?,
    startColor: Color,
    endColor: Color
) {
    val (year, month, day) = wandokTimeMillis.toYearMonthDay()
    Box(
        modifier = Modifier
            .padding(top = 25.dp)
            .height(54.dp)
            .fillMaxWidth()
            .backGroundWithGradient(shape = footerShape, startColor, endColor),
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
                text = year
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
                text = month
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
                text = day
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

suspend fun extractColorsFromUrl(context: Context, imageUrl: String): Pair<Color, Color> {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .allowHardware(false) // Bitmap 변환을 위해 필요
        .build()

    val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
    val bitmap = (result as? BitmapDrawable)?.bitmap

    return if (bitmap != null) {
        val palette = Palette.from(bitmap).generate()
        val dominant = palette.dominantSwatch?.rgb ?: Orange100.toArgb()
        val vibrant = palette.vibrantSwatch?.rgb ?: Orange500.toArgb()

        Color(dominant) to Color(vibrant)
    } else {
        Orange100 to Orange500
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLabel() {
    val labelState = LabelState.Selected(BookDetail("0000", title = "가나다"))
    WandokLabel(labelState, 5)
}

@Preview(showBackground = true)
@Composable
fun PreviewWandokFooter() {
    WandokFooter(1243252346234L, Orange100, Orange500)
}