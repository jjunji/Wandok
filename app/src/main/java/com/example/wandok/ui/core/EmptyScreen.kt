package com.example.wandok.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wandok.R

@Composable
fun EmptyScreen(modifier: Modifier) {
    Box(
        contentAlignment = TopCenter,
        modifier = modifier
            .fillMaxSize()
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val yOffset = (placeable.height * 0.3f).toInt()
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.place(
                        x = (constraints.maxWidth - placeable.width) / 2,
                        y = yOffset
                    )
                }
            }
    ) {
        BodyLargeText(
            text = stringResource(R.string.message_add_book_to_read)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyScreen() {
    EmptyScreen(modifier = Modifier.fillMaxSize())
}