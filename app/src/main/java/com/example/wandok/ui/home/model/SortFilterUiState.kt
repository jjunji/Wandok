package com.example.wandok.ui.home.model

data class SortFilterUiState(
    val show: Boolean = false,
    val selectedFilter: SortType = SortType.Newest
)

sealed interface SortType {
    data object Newest: SortType
    data object Oldest: SortType
    data object HighestProgress: SortType
    data object LowestProgress: SortType
}