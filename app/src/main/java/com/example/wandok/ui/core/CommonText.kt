package com.example.wandok.ui.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.Typography

@Composable
fun Body1Text(
    modifier: Modifier,
    text: String,
    color: Color = DarkGray,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.body1
    )
}

@Composable
fun Body2Text(
    modifier: Modifier,
    text: String,
    color: Color = DarkGray,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.body2
    )
}