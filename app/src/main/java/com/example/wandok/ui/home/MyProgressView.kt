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
import androidx.compose.ui.geometry.Rect
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

val frameWidth = 280.dp
val frameHeight = 430.dp
val frameRadius = 164.dp
val progressWidth = 250.dp
val progressHeight = 390.dp

val progressRadius = 141.dp

val strokeWidth = 8.dp
val moveOffset = 80.dp

/*
    frame
        - background view (음영)
        - progress view (진행률)
 */
@Composable
fun MyOvalProgressView(
    modifier: Modifier
) {
    Box(modifier = modifier) {
        ShadowContainer(
            modifier = Modifier
                .align(Alignment.CenterEnd)
//                .offset(moveOffset)
                .background(color = Color.White),
            radius = 164.dp
        ) {
            RectFrame {
                ProgressBackground(modifier = Modifier)  // custom progress view
                ProgressSample()
            }
        }
    }
}

@Composable
fun RectFrame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .width(frameWidth)
            .height(frameHeight)
            .background(shape = RoundedCornerShape(frameRadius), color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun ProgressBackground(modifier: Modifier) {
    Canvas(
        modifier = modifier
            .width(progressWidth)
            .height(progressHeight)
    ) {
        drawRoundRect(
            color = Orange100,
            cornerRadius = CornerRadius(progressRadius.toPx(), progressRadius.toPx()),
            topLeft = Offset(0f, 0f),
            size = Size(progressWidth.toPx(), progressHeight.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )
    }
}

@Composable
fun ProgressSample() {
    val startOffset = Pair(progressWidth.toPx(), progressHeight.toPx() / 2)
    val straightLength = progressHeight.toPx() - (2 * progressRadius.toPx()) // 직선의 길이 (세로 변에서 곡선이 아닌 영역)

    val wPx = progressWidth.toPx()
    val hPx = progressHeight.toPx()
    val radius = progressRadius.toPx()

    val topRect = Rect(0f, 0f, wPx, hPx - radius)
    val bottomRect = Rect(0f, radius, wPx, hPx)

    val path = Path().apply {
        moveTo(startOffset.first, startOffset.second)
        lineTo(startOffset.first, startOffset.second - straightLength / 2)
        arcTo(
            topRect,
            0f,
            -180f,
            false
        )
        arcTo(
            bottomRect,
            -180f,
            -180f,
            false
        )
        lineTo(wPx, hPx - radius - straightLength / 2)
    }

    Canvas(
        modifier = Modifier
            .width(250.dp)
            .height(390.dp)
    ) {
        drawPath(
            path = path,
            color = Orange800,
            style = Stroke(width = strokeWidth.toPx())
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

@Preview(showBackground = true)
@Composable
fun PreviewArchSample() {
    ProgressSample()
}