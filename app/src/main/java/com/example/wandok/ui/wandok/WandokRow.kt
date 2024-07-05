package com.example.wandok.ui.wandok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wandok.R

@Composable
fun WandokRow(yComponent: Float, rotationDegree: Float) {
    Box(
        modifier = Modifier
            .width(110.dp)
            .height(150.dp)
            .graphicsLayer {
                translationY = yComponent
                rotationZ = rotationDegree
            }
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