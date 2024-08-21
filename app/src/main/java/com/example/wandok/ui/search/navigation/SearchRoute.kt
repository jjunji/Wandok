package com.example.wandok.ui.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wandok.ui.search.SearchDetailScreen
import com.example.wandok.ui.search.SearchDetailViewModel
import com.example.wandok.ui.search.SearchScreen
import com.example.wandok.ui.search.SearchViewModel
import timber.log.Timber

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    onItemClick: (isbn: String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Timber.tag("test").e("--")
    SearchScreen(
        onItemClick = { isbn: String -> onItemClick(isbn) }
    )
}

@Composable
fun SearchDetailRoute(
    onBackClicked: () -> Unit,
    onAddCompleted: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel()
) {
    SearchDetailScreen(
        onBackClicked = { onBackClicked() },
        onAddCompleted = { onAddCompleted() },
        viewModel = viewModel
    )
}