package com.example.wandok.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalMaterialApi
@Composable
fun SwipeRefreshBox(refreshing: Boolean, pullRefreshState: PullRefreshState, content: @Composable () -> Unit) {
    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        content()
    }
}