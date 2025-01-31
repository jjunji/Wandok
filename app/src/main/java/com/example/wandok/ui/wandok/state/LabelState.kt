package com.example.wandok.ui.wandok.state

import com.example.wandok.data.model.BookDetail

sealed class LabelState<out T> {
    data object None : LabelState<Nothing>()
    data class Selected(val bookDetail: BookDetail) : LabelState<BookDetail>()
}