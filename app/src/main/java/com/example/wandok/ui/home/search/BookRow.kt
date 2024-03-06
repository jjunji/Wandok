package com.example.wandok.ui.home.search

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wandok.R
import com.example.wandok.data.model.Book
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.GrayC1
import com.example.wandok.ui.theme.Typography

@Composable
fun BookRow(book: Book) {
    Box(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
    ) {
        Row {
            BookImage(
                imageUrl = "",
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier
                    .background(color = GrayC1)
            )
            BookInfo(book, modifier = Modifier)
        }
    }
}

@Composable
fun BookImage(imageUrl: String, modifier: Modifier) {
    Box(
        modifier = modifier.height(IntrinsicSize.Max)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_placeholder_book),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp)
        )
    }
}

@Composable
fun VerticalDashLine(modifier: Modifier) {
    Canvas(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .padding(10.dp)
            .background(Color.White),
    ) {
        drawLine(
            color = GrayC1,
            strokeWidth = 1f,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 10f)
        )
    }
}

@Composable
fun BookInfo(book: Book, modifier: Modifier) {
    Column(
        modifier = modifier.height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = book.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            style = Typography.body1
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = book.author,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = DarkGray,
            style = Typography.body2
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = book.publisher,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = DarkGray,
            style = Typography.body2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookImage() {
    BookImage(
        imageUrl = "",
        Modifier.height(130.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBookInfo() {
    BookInfo(
        Book(
            title = "책 제목 : 코틀린 함수형 프로그래밍 - 코틀린으로 제대로 함수형 프로그래밍 익히기",
            author = "저자 : 마르코 버뮬런.루나르 비아르드나손.폴 치우사노 지음, 오현석 외 옮김",
            publisher = "출판사"
        ),
        Modifier.height(130.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewVerticalDashLine() {
    VerticalDashLine(modifier = Modifier.height(130.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewBookRow() {
    Row {
        PreviewBookImage()
        PreviewVerticalDashLine()
        PreviewBookInfo()
    }
}

