package com.example.wandok.ui.core

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wandok.ui.theme.Orange100
import com.example.wandok.ui.theme.Orange300

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    height: Dp = 8.dp,
    color: Color = Orange300,
    backGroundColor: Color = Orange100
) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(8.dp)),
        color = color,
        backgroundColor = backGroundColor
    )
}