package com.example.wandok.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    text: String = "Empty"
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.Red,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}