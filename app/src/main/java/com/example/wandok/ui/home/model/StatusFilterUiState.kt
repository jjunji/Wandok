package com.example.wandok.ui.home.model

data class StatusFilterUiState(
    val show: Boolean = false,
    val selectedFilter: BookStatus = BookStatus.All
)

sealed interface BookStatus {
    data object All : BookStatus
    data object Reading : BookStatus    // 0 < progress
    data object Done : BookStatus       // progress == 100
    data object ToRead : BookStatus     // progress == 0
}