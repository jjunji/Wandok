package com.example.wandok.ui.wandok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.toDouble()
    val itemWidth = 97

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(
            items = items,
            key = { index, item ->
                item
            }
        ) { index, item ->
            Box(modifier = modifier.wrapContentSize()) {
                // Apply arch effect here
                val xOffset = (screenWidth / 2) - (itemWidth / 2) - (80 * index)
                val (yComponent, alpha) = computeYComponent(xOffset + itemWidth / 2, screenWidth)
                val rotation = (alpha * (180 / PI)).toFloat() - 90f

                WandokRow(
                    modifier = Modifier
                        .width(97.dp)
                        .height(157.dp)
                        .offset(
                            x = xOffset
                                .toFloat()
                                .pxToDp(), y = yComponent.dp
                        )
//                        .graphicsLayer(rotationZ = rotation)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewWandokList() {
    HorizontalWandokList(items = listOf("1", "2"))
}

@Composable
fun HorizontalWandokList2(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
    val itemWidth = 97.dp.toPx()

    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> item }
        ) { index, item ->
            val leftPoint = (screenWidth / 2) - (itemWidth / 2) + (80 * index)
            Timber.tag("test").e("leftPos : ${leftPoint.pxToDp()}")

            val (yComponent, alpha) = computeYComponent((leftPoint + itemWidth / 2).toDouble(), screenWidth.toDouble())
            val rotation = (alpha * (180 / PI)).toFloat() - 90f

            WandokRow(
                modifier = Modifier
                    .width(97.dp)
                    .height(157.dp)
                    .offset(
                        x = leftPoint.pxToDp(),
                        y = yComponent.dp
                    )
                    .graphicsLayer(rotationZ = rotation)
            )
        }
    }
}

private fun computeYComponent(centerX: Double, screenWidth: Double): Pair<Float, Float> {
    val halfWidth = screenWidth / 2
    val radius = halfWidth * 2

    val xScreenFraction = centerX / screenWidth

    val beta = acos(halfWidth / radius)
    val alpha = beta + (xScreenFraction * (PI - (2 * beta)))
    val yComponent = radius - (radius * sin(alpha))

    return Pair(yComponent.toFloat(), alpha.toFloat())
}