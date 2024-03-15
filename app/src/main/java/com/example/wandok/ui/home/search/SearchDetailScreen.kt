package com.example.wandok.ui.home.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchDetailScreen(viewModel: SearchDetailViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.clickable {
            },
            text = "상세 페이지 /"
        )
    }
}

@Composable
fun AddBookButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "추가하기"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddBookButton() {
    AddBookButton()
}