package com.example.wandok.ui.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.Typography

@Composable
fun Body1Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = DarkGray,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.body1,
        textAlign = textAlign
    )
}

@Composable
fun Body2Text(
    modifier: Modifier,
    text: String,
    color: Color = DarkGray,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.body2,
        textAlign = textAlign
    )
}