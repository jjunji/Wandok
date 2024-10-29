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
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.common.extension.toPx
import com.example.wandok.ui.core.ShadowContainer
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange800
import timber.log.Timber
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt

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
                .offset(moveOffset)
                .background(color = Color.White),
            radius = 164.dp
        ) {
            RectFrame {
                ProgressBackground(modifier = Modifier.offset())  // custom progress view
//                ProgressSample(modifier = Modifier.offset())
                Calc()
            }
        }

        VerticalLine(modifier = Modifier.align(Alignment.TopEnd))
    }
}

@Composable
fun VerticalLine(
    modifier: Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        drawLine(
            color = Color.Red,
            start = Offset(0f, 0f),
            end = Offset(0f, frameHeight.toPx()),
            strokeWidth = 8.dp.toPx()
        )
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
 *  3. tan-1 (밑변 / 높이) -> 각도 || cos-1(r / 밑변) -> x축 기준 교점까지 각도
 *  4. 각도 / pie -> 180도 기준으로 각도가 가지는 비율
 *  5. 비율에 따른 길이
 *  6. startOffset 부터 교점 까지의 구간 중 직선이 가지는 길이
 *  7. 제외할 영역 (직선 + 곡선) / 전체 영역 (반원 호 + 양 끝 직선) -> 제외할 구간의 비율
 */
@Composable
fun Calc() {
    val progressWidthPx = progressWidth.toPx()
    val progressHeightPx = progressHeight.toPx()
    val frameRadiusPx = frameRadius.toPx()
    val moveOffsetPx = moveOffset.toPx()
    val progressRadiusPx = progressRadius.toPx()

    // 1.
    val bottomLine = frameRadiusPx - moveOffsetPx
    Timber.tag("calc").e("bottomLine : $bottomLine")
    // 2.
    val h = sqrt(progressRadiusPx.pow(2) - bottomLine.pow(2))
    Timber.tag("calc").e("h: $h")

    // 3.
    val angle = atan(h / bottomLine)
    val angle2 = acos(bottomLine / progressRadiusPx)
    Timber.tag("calc").e("a : $angle / b : $angle2")

    // 4. 비율
    val angleRatio = angle / PI
    Timber.tag("calc").e("angleRatio : $angleRatio")

    // 4.
    val angleRatio2 = angle / (2 * PI)
    Timber.tag("calc").e("angleRatio : $angleRatio2")

    // 5.
    val halfCircumference = PI * progressRadiusPx
    val arcDistance = halfCircumference * angleRatio
    Timber.tag("calc").e("distance : $arcDistance")

    // 5.
    val circumference = 2 * PI * progressRadiusPx   // 호
    val arcDistance2 = circumference * angleRatio2  // 곡선 길이

    // 6.
    val straightDistance = (progressHeightPx - progressWidthPx) / 2
    Timber.tag("calc").e("straightDistance : $straightDistance")

    // 6.
    val straightDistance2 = (progressHeightPx - progressWidthPx)    // 한쪽 직선 길이
    Timber.tag("calc").e("straightDistance2 : $straightDistance2")

    // 7.
    val cutRatio = (arcDistance + straightDistance) / (PI * progressRadiusPx + straightDistance * 2)
    Timber.tag("calc").e("cutRatio : $cutRatio")

    val cutRatio2 = (arcDistance2 + straightDistance2) / (2 * PI + progressRadiusPx + straightDistance2 * 2)

    // TODO: 직선 + 위 곡선 아래 곡선 길이 합 / 전체 길이

    val startOffset = Pair(progressWidthPx, progressHeightPx / 2)
    val straightLength = progressHeightPx - (2 * progressRadiusPx)  // 직선의 길이 (세로 변에서 곡선이 아닌 영역)
    val topRect = Rect(0f, 0f, progressWidthPx, progressHeightPx - progressRadiusPx)
    val bottomRect = Rect(0f, progressRadiusPx, progressWidthPx, progressHeightPx)

    val completePath = Path().apply {
        moveTo(startOffset.first, startOffset.second)
        lineTo(startOffset.first, startOffset.second - straightLength / 2)
        arcTo(
            topRect,
            0f,
            -180f,
            false
        )
        lineTo(0f, progressRadiusPx + straightLength)
        arcTo(
            bottomRect,
            -180f,
            -180f,
            false
        )
        lineTo(progressWidthPx, progressHeightPx - progressRadiusPx - straightLength / 2)
    }

    val completePathMeasure = PathMeasure().apply {
        setPath(completePath, false)
    }

//    val progress = (1 / 100f) * (1 - cutRatio2) + cutRatio2
    val progress = cutRatio2

    val pathMeasure = PathMeasure()
    val trimmedPath = Path()

    pathMeasure.setPath(completePath, false)
    pathMeasure.getSegment(
        progress.toFloat(),
        (progress * pathMeasure.length).toFloat(),
        trimmedPath,
        true
    )

    Canvas(
        modifier = Modifier
            .width(250.dp)
            .height(390.dp)
    ) {
        drawPath(
            path = trimmedPath,
            color = Orange800,
            style = Stroke(width = strokeWidth.toPx())
        )
    }
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
        modifier = Modifier.fillMaxSize()
    ) {
        MyOvalProgressView(modifier = Modifier)
    }
}