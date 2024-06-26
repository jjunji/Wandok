package com.example.wandok.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.common.extension.pxToDp
import com.example.wandok.ui.wandok.WandokRow

@Composable
fun test() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        WandokRow(
            modifier = Modifier
                .offset(x = 80.pxToDp())
                .size(width = 97.dp, height = 157.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTest() {
    test()
}