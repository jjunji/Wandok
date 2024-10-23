package com.example.wandok.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import timber.log.Timber
import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

val frameWidth = 280.dp
val frameHeight = 430.dp
val frameRadius = 164.dp
val progressWidth = 250.dp
val progressHeight = 390.dp

val progressRadius = 141.dp

val strokeWidth = 8.dp
val moveOffset = 0.dp

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
//                .offset(moveOffset)
                .background(color = Color.White),
            radius = 164.dp
        ) {
            RectFrame {
                ProgressBackground(modifier = Modifier.offset(moveOffset))  // custom progress view
                ProgressSample(modifier = Modifier.offset(moveOffset))
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

/**
 *  1. Background R - offset -> 밑변(w)
 *  2. (Inner R)^ - 밑변^ 의 루트 -> 높이(h) (밑변^ + 높이^ = Inner R^)
 *  3. tan-1 (밑변 / 높이) -> 각도
 *  4. cos-1(r / 밑변) -> 각도
 */
@Preview(showBackground = true)
@Composable
fun Calc() {
    // 1.
    val w = (164 - 80).toFloat()
    Timber.tag("calc").e("w : $w")
    // 2.
    val h = sqrt(141.0.pow(2) - w.pow(2))
    val h2 = 141.0.pow(2)
    val h3 = w.pow(2)

    Timber.tag("calc").e("h: $h / h2: $h2 / h3: $h3")

    val a = atan2(w.toDouble(), h)
    val b = acos(w / 141.0)

    Timber.tag("test").e("a : $a / b : $b")
}

@Composable
fun Progress() {

}

@Composable
fun ProgressSample(modifier: Modifier) {
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
        modifier = modifier
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewArchSample() {
//    ProgressSample()
//}