package com.example.wandok.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.Orange300

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val orangeColor = Orange300
    val grayColor = DarkGray
    val shape = RoundedCornerShape(24.dp)

    val borderColor = if (isSelected) orangeColor else grayColor
    val borderWidth = if (isSelected) 2.dp else 1.dp

    Box(
        modifier = modifier
            .clip(shape)
            .background(color = White)
            .border(
                border = BorderStroke(borderWidth, borderColor),
                shape = shape
            )
            .clickable { onClick() }
            .padding(start = 16.dp, end = 16.dp)
            .width(IntrinsicSize.Max)
            .height(38.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) orangeColor else grayColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterTextView() {
    FilterItem(text = "등록 순", isSelected = true) {}
}
