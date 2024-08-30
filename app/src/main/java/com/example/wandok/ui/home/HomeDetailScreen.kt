package com.example.wandok.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wandok.R
import com.example.wandok.data.model.BookDetail
import com.example.wandok.ui.core.BodyMediumText
import com.example.wandok.ui.core.CustomAppBar
import com.example.wandok.ui.core.IconAttr
import com.example.wandok.ui.core.RoundedRectText
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange500
import com.example.wandok.ui.theme.Orange800

@Composable
fun HomeDetailRoute(
    viewModel: HomeDetailViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val myBook by viewModel.myBook.collectAsState(null)
    myBook?.let {
        HomeDetailScreen(
            myBook = it,
            onBackClicked = { onBackClicked() }
        )
    }
}

@Composable
fun HomeDetailScreen(
    myBook: BookDetail,
    onBackClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomAppBar(
            modifier = Modifier,
            onBackClicked = { onBackClicked() },
            title = myBook.title
        )

        if (myBook.tableOfContents.isEmpty()) return

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ProgressInfo()
            }

            itemsIndexed(
                items = myBook.tableOfContents
            ) { _, item ->
                IndexRow(tableOfContent = item)
            }
        }
    }
}

@Composable
fun DDAY(modifier: Modifier) {
    Column(
        modifier = modifier.wrapContentSize()
    ) {
        BodyMediumText(
            text = "D - DAY",
            color = Orange800,
            bold = true
        )
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "30",
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            color = Orange500
        )
        BodyMediumText(text = "설정 목표", color = Orange500)
        BodyMediumText(
            modifier = Modifier.padding(top = 3.dp),
            text = "2024. 8. 28 ~ 2024. 8. 28",
            color = Orange500
        )

        RoundedRectText(
            modifier = Modifier.padding(top = 10.dp),
            text = "목표 재설정",
            cornerRadius = 16.dp,
            iconAttr = IconAttr(painter = painterResource(id = R.drawable.ic_edit_goal)),
            onClick = {
                // TODO: Dialog
            }
        )
    }
}

@Composable
fun OvalProgressBar(modifier: Modifier) {
    val radius = 141.dp
    val width = 282.dp
    val height = 424.dp

    Canvas(
        modifier = modifier
            .width(width)
            .height(height)
    ) {
        drawRoundRect(
            color = Orange100,
            cornerRadius = CornerRadius(radius.toPx(), radius.toPx()),
            topLeft = Offset(0f, 0f),
            size = Size(width.toPx(), height.toPx()),
            style = Stroke(width = 8.dp.toPx())
        )
    }
}

@Composable
fun ProgressInfo() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            OvalProgressBar(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.Center)
                    .offset(x = (100).dp)
            )

            DDAY(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 16.dp, start = 16.dp)
            )
        }
    }
}