package com.example.wandok.ui.wandok

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wandok.R
import com.example.wandok.ui.theme.Orange100

@Composable
fun WandokRow(modifier: Modifier) {
    Box(
        modifier = modifier
//            .width(100.dp)
//            .height(140.dp)
            .background(Orange100, RoundedCornerShape(20.dp))
    ) {
        AsyncImage(
            model = "https://image.aladin.co.kr/product/32064/57/coversum/k212834895_1.jpg",
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.img_sample),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Preview
@Composable
fun PreviewWandokRow() {
    WandokRow(modifier = Modifier)
}