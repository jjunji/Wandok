package com.example.wandok.ui.home.model

data class StatusFilterUiState(
    val show: Boolean = false,
    val selectedFilter: BookStatus = BookStatus.All
)

sealed interface BookStatus {
    data object All : BookStatus
    data object Reading : BookStatus
    data object Done : BookStatus
    data object ToRead : BookStatus
}