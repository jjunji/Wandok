package com.example.wandok.ui.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wandok.ui.search.SearchScreen
import com.example.wandok.ui.search.SearchViewModel

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen(
        onItemClick = { isbn: String -> run {} }
    )
}