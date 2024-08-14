package com.example.wandok.ui.home.model

import com.example.wandok.data.model.BookDetail

data class SortFilterUiState(
    val show: Boolean = false,
    val selectedFilter: SortType = SortType.Newest
)

enum class SortType(
    val title: String,
    val comparator: Comparator<BookDetail>
) {
    Newest("최신 등록 순", compareByDescending { it.registrationTimeMillis }),
    Oldest("오래된 등록 순", compareBy { it.registrationTimeMillis }),
    HighestProgress("진행률 높은 순", compareByDescending { it.progress }),
    LowestProgress("진행률 낮은 순", compareBy { it.progress })
}
