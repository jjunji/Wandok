package com.example.wandok.ui.core

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wandok.ui.theme.GrayC1

fun Modifier.grayRoundCorner(): Modifier {
    return this.border(
        width = 2.dp,
        color = GrayC1,
        shape = RoundedCornerShape(10.dp)
    )
}