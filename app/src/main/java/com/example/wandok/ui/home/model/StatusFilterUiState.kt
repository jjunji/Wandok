package com.example.wandok.ui.home.model

import com.example.wandok.data.model.BookDetail

data class StatusFilterUiState(
    val show: Boolean = false,
    val selectedFilter: BookStatus = BookStatus.All
)

@Suppress("MagicNumber")
enum class BookStatus(val filter: (BookDetail) -> Boolean) {
    All({ true }),
    Reading({ it.progress in 1..99 }),
    Done({ it.progress == 100 }),
    ToRead({ it.progress == 0 })
}