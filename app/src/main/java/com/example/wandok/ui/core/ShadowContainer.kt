package com.example.wandok.ui.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wandok.ui.theme.LightShadow
import com.example.wandok.ui.theme.Orange100

@Composable
fun ShadowContainer(
    modifier: Modifier = Modifier,
    color: Color = LightShadow,
    radius: Dp = 16.dp,
    blurRadius: Dp = 16.dp,
    offset: Pair<Dp, Dp> = Pair(1.dp, 1.dp),
    spread: Dp = 3.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .padding(horizontal = 3.dp, vertical = 2.dp)
            .shadow(
                color,
                borderRadius = radius,
                offsetX = offset.first,
                offsetY = offset.second,
                spread = spread,
                blurRadius = blurRadius
            )
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShadowContainer() {
    ShadowContainer(modifier = Modifier.background(color = Color.White)) {
        Canvas(modifier = Modifier.size(100.dp, 100.dp)) {
            drawCircle(Orange100)
        }
    }
}