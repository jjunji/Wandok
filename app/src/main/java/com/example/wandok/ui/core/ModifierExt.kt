package com.example.wandok.ui.core

import android.graphics.BlurMaskFilter
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wandok.ui.theme.DeepShadow
import com.example.wandok.ui.theme.GrayC1
import com.example.wandok.ui.theme.LightShadow

fun Modifier.grayRoundCorner(): Modifier {
    return this.border(
        width = 2.dp,
        color = GrayC1,
        shape = RoundedCornerShape(10.dp)
    )
}

fun Modifier.backGroundWithGradient(
    shape: Shape = RectangleShape,
    startColor: Color,
    endColor: Color
): Modifier {
    return this.background(
        shape = shape,
        brush = Brush.horizontalGradient(
            colors = listOf(
                startColor,
                endColor
            )
        )
    )
}

/**
 * Drop Shadow
 */
fun Modifier.shadow(
    color: Color = DeepShadow,
    borderRadius: Dp = 10.dp,
    blurRadius: Dp = 10.dp,
    offsetY: Dp = 7.dp,
    offsetX: Dp = 7.dp,
    spread: Dp = 7f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel - 3.dp.toPx())
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)

// TODO: shadow() 에서 확장
fun Modifier.shadowExcludeLeft(
    color: Color = LightShadow,
    borderRadius: Dp = 10.dp,
    blurRadius: Dp = 10.dp,
    spread: Dp = 3f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val offsetY = 0f
            val offsetX = spreadPixel * 2 // shadow 좌측 시작점 조정

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter = BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL)
            }

            frameworkPaint.color = color.toArgb()

            val shadowRect = RectF(
                offsetX,
                offsetY - spreadPixel,
                size.width + spreadPixel,
                size.height + spreadPixel
            )

            it.drawRoundRect(
                shadowRect.left,
                shadowRect.top,
                shadowRect.right,
                shadowRect.bottom,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }
)