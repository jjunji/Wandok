package com.example.wandok.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wandok.common.constants.KeyValueConstant.ITEM_PER_PAGE

class PageStatus<T> {
    private val arrItem = ArrayList<T>()
    val itemPerPage = ITEM_PER_PAGE
    var position: Int = 0
        private set

    private val _items = MutableLiveData<List<T>>()
    val items: LiveData<List<T>> = _items

    private val _countOfAllItems = MutableLiveData<Int>()
    val countOfAllItems: LiveData<Int> = _countOfAllItems

    private val _hasMore: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasMore: LiveData<Boolean> = _hasMore

    fun notifyPageStatusChanged(newItems: List<T>, total: Int) {
        arrItem.addAll(newItems)
        position = arrItem.size

        _countOfAllItems.value = total
        _hasMore.value = total > arrItem.size
        _items.value = arrItem
    }

    fun init() {
        _hasMore.value = true // TODO: 2021-04-01
        position = 0
        arrItem.clear()
        _countOfAllItems.value = 0
        _items.value = emptyList()
    }

    fun firstPage(): Boolean {
        return (position == 0)
    }
}