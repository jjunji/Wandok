package com.example.wandok.ui.wandok

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.wandok.common.extension.pxToDp
import com.example.wandok.common.extension.toPx
import timber.log.Timber
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.sin

@Composable
fun HorizontalWandokList(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
    val itemWidth = 97.dp.toPx()

    val contentPadding = remember {
        PaddingValues(start = ((screenWidth - itemWidth) / 2).pxToDp())
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = contentPadding
    ) {
        itemsIndexed(items) { index, _ ->
            val itemOffset = (listState.layoutInfo.visibleItemsInfo
                .find { it.index == index }?.offset ?: 0).toFloat()

            if (index == 0) {
                val centerX = (screenWidth / 2) + itemOffset - itemWidth
                val (yComponent, alpha) = computeYComponent(centerX.toDouble(), screenWidth.toDouble())
                Timber.tag("test").e("y: $yComponent / alpha: $alpha / centerX: $centerX")
            }

            val centerX = (screenWidth / 2) + itemOffset - (itemWidth / 2)
            val (yComponent, alpha) = computeYComponent(centerX.toDouble(), screenWidth.toDouble())

            WandokRow(
                modifier = Modifier
                    .width(97.dp)
                    .height(157.dp)
                    .graphicsLayer {
                        translationY = yComponent
                        rotationZ = (alpha * (180 / PI)).toFloat() - 90f
                    }
            )
        }
    }
}

fun Offset.toIntOffset() = IntOffset(x.toInt(), y.toInt())

private fun computeYComponent(centerX: Double, screenWidth: Double): Pair<Float, Float> {
    val halfWidth = screenWidth / 2
    val radius = halfWidth * 2

    val xScreenFraction = centerX / screenWidth

    val beta = acos(halfWidth / radius)
    val alpha = beta + (xScreenFraction * (PI - (2 * beta)))
    val yComponent = radius - (radius * sin(alpha))

    return Pair(yComponent.toFloat(), alpha.toFloat())
}