package com.example.wandok.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wandok.R
import com.example.wandok.database.BookEntity
import com.example.wandok.ui.core.Body1Text
import com.example.wandok.ui.core.Body2Text
import com.example.wandok.ui.core.LinearProgressBar
import com.example.wandok.ui.core.shadow
import com.example.wandok.ui.theme.Typography
import timber.log.Timber

val shadowColor = Color(0x4D000000)
val CornerRadius = 10.dp

@Composable
fun MyBookRow(myBook: BookEntity, onItemClicked: () -> Unit) {
    // Shadow Container
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp)
            .shadow(
                shadowColor,
                borderRadius = CornerRadius,
                offsetX = 7.dp,
                offsetY = 7.dp,
                spread = 7.dp,
                blurRadius = CornerRadius
            )
            .clickable { onItemClicked() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(CornerRadius))
                .background(Color.White)
        ) {
            Row(modifier = Modifier.height(150.dp)) {
                AsyncImage(
                    model = myBook.image,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_placeholder_book),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(110.dp)
                        .height(120.dp)
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterVertically)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 10.dp)
                ) {
                    Body1Text(text = "4 / 30")

                    Row(
                        modifier = Modifier.padding(top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LinearProgressBar(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 5.dp),
                            progress = 0.5f
                        )

                        Body2Text(text = "40%")
                    }

                    Text(
                        modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                        text = myBook.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                        style = Typography.body1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyBookRow() {
    MyBookRow(
        myBook = BookEntity(
            "123",
            "title",
            "",
            "",
            "")
    ) {}
}