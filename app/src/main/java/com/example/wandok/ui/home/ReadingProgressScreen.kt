package com.example.wandok.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeDetailRoute(
    onBackClicked: () -> Unit
) {
    HomeDetailScreen(
        onBackClicked = { onBackClicked() }
    )
}

@Composable
fun HomeDetailScreen(
    onBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopCenter)
                .height(40.dp),
            onClick = { onBackClicked() }
        ) {
            Text(text = "Back")
        }
    }
}