package com.example.wandok.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wandok.ui.home.HomeScreen
import com.example.wandok.ui.home.HomeViewModel

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel:HomeViewModel = hiltViewModel()
) {
    HomeScreen()
}