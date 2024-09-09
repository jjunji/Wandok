package com.example.wandok.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.common.extension.toPx
import com.example.wandok.ui.core.ShadowContainer
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange800

@Composable
fun MyOvalProgressView(
    modifier: Modifier
) {
    ShadowContainer(
        modifier = modifier
            .background(color = Color.White),
//            .offset(x = (100).dp),
        radius = 164.dp
    ) {
        BackgroundView {
            MyOvalProgressBar(modifier = Modifier)
            ArchSample()
        }
    }
}

@Composable
fun MyOvalProgressBar(modifier: Modifier) {
    val radius = 141.dp
    val width = 250.dp
    val height = 390.dp

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
fun BackgroundView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .width(280.dp)
            .height(430.dp)
            .background(shape = RoundedCornerShape(164.dp), color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun ArchSample() {
    val radius = 141.dp.toPx()
    val frameWidth = 250.dp.toPx()
    val frameHeight = 390.dp.toPx()
    val straightLength = frameHeight - (2 * radius) // 직선의 길이 (세로 변에서 곡선이 아닌 영역)

    val path = Path().apply {
        moveTo(frameWidth, frameHeight / 2)
        lineTo(frameWidth, frameHeight / 2 - straightLength / 2)
    }

    Canvas(
        modifier = Modifier
            .width(250.dp)
            .height(390.dp)
    ) {
        drawPath(
            path = path,
            color = Orange800,
            style = Stroke(width = 8.dp.toPx())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOvalProgress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MyOvalProgressView(modifier = Modifier)
    }
}