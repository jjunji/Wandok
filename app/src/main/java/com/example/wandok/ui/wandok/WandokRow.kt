package com.example.wandok.ui.wandok

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wandok.R
import com.example.wandok.data.model.BookDetail

@Composable
fun WandokRow(
    modifier: Modifier,
    yComponent: Float,
    rotationDegree: Float,
    bookDetail: BookDetail,
    onItemClicked: (clickedItem: BookDetail) -> Unit
) {
    Box(
        modifier = modifier
            .width(wandokItemWidth)
            .height(wandokItemHeight)
            .wrapContentHeight()
            .graphicsLayer {
                translationY = yComponent
                rotationZ = rotationDegree
            }
            .clickable {
                onItemClicked(bookDetail)
            }
    ) {
        AsyncImage(
            model = bookDetail.image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_placeholder_book),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
    }
}