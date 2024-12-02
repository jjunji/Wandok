package com.example.wandok.ui.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import kotlinx.coroutines.launch
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

@Composable
fun MyOvalProgressView(
    modifier: Modifier,
    progress: Int
) {
    Box(modifier = modifier.offset(moveOffset)) {
        ShadowContainer(
            modifier = Modifier
                .offset()
                .background(color = Color.White),
            radius = 164.dp
        ) {
            RectFrame {
                ProgressBackground(modifier = Modifier.offset())  // custom progress view
                ProgressBar(progress)
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
 *  3. tan-1 (밑변 / 높이) -> 각도 || cos-1(r / 밑변) -> x축 기준 교점까지 각도
 *  4. 각도 / pie -> 180도 기준으로 각도가 가지는 비율
 *  5. 비율에 따른 길이
 *  6. startOffset 부터 교점 까지의 구간 중 직선이 가지는 길이
 *  7. 제외할 영역 (직선 + 곡선) / 전체 영역 (반원 호 + 양 끝 직선) -> 제외할 구간의 비율
 */
@Composable
fun ProgressBar(progress: Int) {
    val progressWidthPx = progressWidth.toPx()
    val progressHeightPx = progressHeight.toPx()
    val progressRadiusPx = progressRadius.toPx()
    val frameRadiusPx = frameRadius.toPx()
    val moveOffsetPx = moveOffset.toPx()

    val verticalWithoutCurves =
        progressHeightPx - (progressRadiusPx * 2)  // 직선의 길이 (세로 변에서 곡선이 아닌 영역)

    // 1. 밑변
    val bottomLine = frameRadiusPx - moveOffsetPx

    // 2. 높이
    val height = sqrt(progressRadiusPx.pow(2) - bottomLine.pow(2))

    // 3. (밑변 높이 빗변(radius) 를 이은 직각 삼각형에서 빗변의 각도))
    val angle = atan(height / bottomLine)

    // 4. 비율
    val angleRatio = angle / PI

    // 5.
    val halfCircumference = PI * progressRadiusPx // 반원 길이
    val arcDistance = halfCircumference * angleRatio

    // 6. Progress Bar 에서 제외할 경로의 비율
    val cutRatio =
        (arcDistance + verticalWithoutCurves) / (PI * progressRadiusPx + verticalWithoutCurves * 2)

    val startOffset = Pair(progressWidthPx, progressHeightPx / 2)
    val topRect = Rect(0f, 0f, progressWidthPx, progressHeightPx - progressRadiusPx)
    val bottomRect = Rect(0f, progressRadiusPx, progressWidthPx, progressHeightPx)

    val topPartPath = Path().apply {
        moveTo(startOffset.first, startOffset.second)
        lineTo(startOffset.first, startOffset.second - verticalWithoutCurves / 2)
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

    val combinedPath = Path()   // Rect 에서 위(진행률 경로) 와 아래(진행률 경로) 를 합친 경로

    val trimmedTopPartMeasured = PathMeasure()
    trimmedTopPartMeasured.setPath(trimmedTopPart, false)
    trimmedTopPartMeasured.getSegment(
        0f,
        stopDistance = trimmedTopPartMeasured.length,
        destination = combinedPath,
        true
    )

    val bottomPartPath = Path().apply {
        moveTo(0f, startOffset.second)
        lineTo(0f, startOffset.second + verticalWithoutCurves / 2)
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

    val trimmedBottomPartMeasured = PathMeasure()
    trimmedBottomPartMeasured.setPath(trimmedBottomPart, false)
    trimmedBottomPartMeasured.getSegment(
        startDistance = 0f,
        stopDistance = trimmedBottomPartMeasured.length,
        destination = combinedPath,
        false
    )

    val combinePathMeasure = PathMeasure()
    combinePathMeasure.setPath(combinedPath, false)

    val percent = progress.toFloat() / 100f
    val progressPath = Path()
    combinePathMeasure.getSegment(
        startDistance = 0f,
        stopDistance = combinePathMeasure.length * percent,
        destination = progressPath
    )

    Draw(progressPath)
}

@Composable
fun Draw(progressPath: Path) {
    val animatable = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        launch {
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 3000)
            )
        }
    }

    // 진행률 PathMeasure
    val progressPathMeasure = PathMeasure().apply {
        setPath(progressPath, false)
    }

    // 애니메이션 진행률에 따른 Path
    val animatedPath = Path()
    progressPathMeasure.getSegment(
        startDistance = 0f,
        stopDistance = progressPathMeasure.length * animatable.value,
        destination = animatedPath,
        true
    )

    val animatedPathMeasure = PathMeasure().apply {
        setPath(animatedPath, false)
    }

    val position = animatedPathMeasure.getPosition(animatedPathMeasure.length)

    Canvas(
        modifier = Modifier
            .width(250.dp)
            .height(390.dp)
    ) {
        drawPath(
            path = animatedPath,
            color = Orange800,
            style = Stroke(width = strokeWidth.toPx())
        )
        drawCircle(
            color = Orange800,
            radius = 8.dp.toPx(),
            center = position
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOvalProgress() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MyOvalProgressView(modifier = Modifier, 70)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressBackground() {
    ProgressBackground(modifier = Modifier)
}