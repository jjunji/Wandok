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
                .offset()
                .background(color = Color.White),
            radius = 164.dp
        ) {
            RectFrame {
                ProgressBackground(modifier = Modifier.offset())  // custom progress view
                Calc()
            }
        }

        VerticalLine(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-80).dp)
        )
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

    val lineLength = progressHeightPx - (progressRadiusPx * 2)  // 직선의 길이 (세로 변에서 곡선이 아닌 영역)

    // 1.
    val bottomLine = frameRadiusPx - moveOffsetPx
    Timber.tag("calc").e("bottomLine : $bottomLine")

    // 2.
    val h = sqrt(progressRadiusPx.pow(2) - bottomLine.pow(2))
    Timber.tag("calc").e("h: $h")

    // 3.
    val angle = atan(h / bottomLine)

    // 4. 비율
    val angleRatio = angle / PI

    // 5.
    val halfCircumference = PI * progressRadiusPx
    val arcDistance = halfCircumference * angleRatio
    Timber.tag("calc").e("distance : $arcDistance")

    // 6.
    val cutRatio = (arcDistance + lineLength) / (PI * progressRadiusPx + lineLength * 2)

    val startOffset = Pair(progressWidthPx, progressHeightPx / 2)
    val topRect = Rect(0f, 0f, progressWidthPx, progressHeightPx - progressRadiusPx)
    val bottomRect = Rect(0f, progressRadiusPx, progressWidthPx, progressHeightPx)

    val topPartPath = Path().apply {
        moveTo(startOffset.first, startOffset.second)
        lineTo(startOffset.first, startOffset.second - lineLength / 2)
        arcTo(topRect, 0f, -180f, false)
        lineTo(0f, startOffset.second)
    }

    val topPartMeasured = PathMeasure()
    val trimmedTopPart = Path()
    topPartMeasured.setPath(topPartPath, false)
    topPartMeasured.getSegment(
        startDistance = (topPartMeasured.length * cutRatio).toFloat(),
        stopDistance = topPartMeasured.length,
        destination = trimmedTopPart
    )

    val trimmedTopPartMeasured = PathMeasure().apply {
        setPath(trimmedTopPart, false)
    }

    val bottomPartPath = Path().apply {
        moveTo(0f, startOffset.second)
        lineTo(0f, startOffset.second + lineLength / 2)
        arcTo(bottomRect, -180f, -180f, false)
        lineTo(startOffset.first, startOffset.second)
    }

    val bottomPartMeasured = PathMeasure()
    val trimmedBottomPart = Path()
    bottomPartMeasured.setPath(bottomPartPath, false)
    bottomPartMeasured.getSegment(
        startDistance = 0f,
        stopDistance = bottomPartMeasured.length - (bottomPartMeasured.length * cutRatio).toFloat(),
        destination = trimmedBottomPart,
    )

    val trimmedBottomPartMeasured = PathMeasure().apply {
        setPath(trimmedBottomPart, false)
    }

    val trimmedPath = Path()
    trimmedPath.addPath(trimmedTopPart)
    trimmedPath.moveTo(0f, startOffset.second)
    trimmedPath.lineTo(0f, startOffset.second + lineLength / 2)
    trimmedPath.addPath(trimmedBottomPart)

    val trimmedPathMeasure = PathMeasure()
    trimmedPathMeasure.setPath(trimmedPath, false)

    Timber.tag("calc").e("${trimmedTopPartMeasured.length}")
    Timber.tag("calc").e("${trimmedBottomPartMeasured.length}")
    Timber.tag("calc").e("${trimmedPathMeasure.length}")

    val progressPath = Path()
    val progressPathMeasure = PathMeasure()
    progressPathMeasure.setPath(trimmedPath, false)
    progressPathMeasure.getSegment(
        startDistance = 0f,
        stopDistance = trimmedPathMeasure.length * 0.9f,
        destination = progressPath
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

@Preview(showBackground = true)
@Composable
fun PreviewOvalProgress() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MyOvalProgressView(modifier = Modifier)
    }
}