package com.example.wandok.common.extension

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current
    return with(density) { this@toPx.toPx() }
}

@Suppress("MagicNumber")
fun Int.dpToPx(): Float {
    val scale = Resources.getSystem().displayMetrics.density
    return this * scale + 0.5f
}

fun Float.pxToDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}

fun Int.pxToDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}