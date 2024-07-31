package com.example.wandok.ui.wandok

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.wandok.common.extension.pxToDp
import com.example.wandok.common.extension.toPx
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.sin

// TODO: List Blink Issue
@Composable
fun HorizontalWandokList(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels
    val itemWidth = wandokItemWidth.toPx()
    var selectedItemIndex by remember {
        mutableStateOf<Int?>(null)
    }

    val isUserScrolling by listState.interactionSource.collectIsDraggedAsState()

    // 첫 번째 아이템의 left, 마지막 아이템의 right 가 스크린 중앙에서 시작, 종료될 수 있도록
    val contentPadding = remember {
        PaddingValues(horizontal = ((screenWidth - itemWidth) / 2).pxToDp())
    }

    LazyRow(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(-20.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        itemsIndexed(items) { index, _ ->
            val itemOffset = (listState.layoutInfo.visibleItemsInfo
                .find { it.index == index }
                ?.offset
                ?: 0).toFloat()

            val animateYOffset by animateFloatAsState(
                targetValue = if (selectedItemIndex == index) {
                    -animYOffset.toPx()
                } else {
                    0f
                }, label = ""
            )

            val centerX = (screenWidth / 2) + itemOffset
            val (yComponent, alpha) = computeYComponent(centerX.toDouble(), screenWidth.toDouble())

            WandokRow(
                modifier = Modifier
                    .padding(bottom = firstItemBottomPadding)
                    .offset(y = animateYOffset.pxToDp()),
                yComponent = yComponent,
                rotationDegree = (alpha * (180 / PI)).toFloat() - 90f,
                onItemClicked = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(index, 0)
                    }
                    coroutineScope.launch {
                        selectedItemIndex = if (selectedItemIndex == index) null else index
                    }
                }
            )
        }
    }

    LaunchedEffect(listState.isScrollInProgress) {
        if (isUserScrolling) {
            selectedItemIndex = null
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

fun Offset.toIntOffset() = IntOffset(x.toInt(), y.toInt())