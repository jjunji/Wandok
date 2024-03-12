package com.example.wandok.data

import androidx.compose.runtime.mutableStateListOf
import com.example.wandok.common.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PageStatus<T> {
    var currentPage: Int = 0
        private set

    var hasMore: Boolean = false
        private set

    private val _items = mutableStateListOf<T>()
    val items: List<T> get() = _items

    private val _loadState = MutableStateFlow(LoadState.IDLE)
    val loadState = _loadState.asStateFlow()

    fun notifyPageStatusChanged(newItems: List<T>, countOfAllItems: Int, page: Int) {
        _items.addAll(newItems)
        currentPage = page
        hasMore = countOfAllItems > items.size
    }

    fun setLoadState(loadState: LoadState) {
        _loadState.value = loadState
    }

    fun init() {
        _items.clear()
        currentPage = 0
        hasMore = false
    }
}