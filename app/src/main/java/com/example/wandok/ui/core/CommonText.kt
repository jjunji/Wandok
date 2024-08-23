package com.example.wandok.ui.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.wandok.ui.theme.Typography

@Composable
fun Body1Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Center,
    bold: Boolean = false
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.bodyLarge,
        textAlign = textAlign,
        fontWeight = if (bold) {
            FontWeight.Bold
        } else {
            Typography.bodyLarge.fontWeight
        }
    )
}

@Composable
fun Body2Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Center,
    bold: Boolean = false
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.bodyMedium,
        textAlign = textAlign,
        fontWeight = if (bold) {
            FontWeight.Bold
        } else {
            Typography.bodyMedium.fontWeight
        }
    )
}

@Composable
fun H6Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Center,
    bold: Boolean = false
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = color,
        style = Typography.titleLarge,
        textAlign = textAlign,
        fontWeight = if (bold) {
            FontWeight.Bold
        } else {
            Typography.titleLarge.fontWeight
        }
    )
}