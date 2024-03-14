package com.example.wandok.ui.home.search

import androidx.compose.foundation.clickable
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber

@Composable
fun SearchDetailScreen(viewModel: SearchViewModel) {
    Surface {
        Text(
            modifier = Modifier.clickable {
                Timber.tag("test").e("${viewModel.bookList.size}")
            },
            text = "상세 페이지"
        )
    }
}