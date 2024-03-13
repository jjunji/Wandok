package com.example.wandok.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.BuildConfig
import com.example.wandok.common.LoadState
import com.example.wandok.common.constants.KeyValueConstant.API_KEY
import com.example.wandok.common.constants.KeyValueConstant.ITEM_PER_PAGE
import com.example.wandok.common.constants.KeyValueConstant.MAX_RESULTS
import com.example.wandok.common.constants.KeyValueConstant.OUTPUT
import com.example.wandok.common.constants.KeyValueConstant.OUTPUT_TYPE_JS
import com.example.wandok.common.constants.KeyValueConstant.QUERY
import com.example.wandok.common.constants.KeyValueConstant.START
import com.example.wandok.common.extension.onError
import com.example.wandok.common.extension.onException
import com.example.wandok.common.extension.onSuccess
import com.example.wandok.data.PageStatus
import com.example.wandok.data.model.Book
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var keyword = MutableStateFlow("")
    val pageStatus = PageStatus<Book>()
    var bookList = pageStatus.items

    fun onKeywordChanged(value: String) {
        viewModelScope.launch {
            keyword.emit(value)
        }
    }

    // TODO: 새로고침 추가
    fun onSearch() {
        pageStatus.init()
        requestBookList(true)
    }

    fun requestBookList(newRequest: Boolean = false) {
        if (newRequest) pageStatus.init()

        val params = params(keyword.value, pageStatus.currentPage + 1)

        viewModelScope.launch {
            pageStatus.setLoadState(LoadState.LOADING)
            repository.getBookList(params)
                .onSuccess {
                    pageStatus.notifyPageStatusChanged(it.items, it.countOfAllItems, it.page)
                }
                .onError { _, message ->
                    // toast message
                }
                .onException {}
            pageStatus.setLoadState(LoadState.IDLE)
        }
    }

    override fun onCleared() {
        pageStatus.init()
        super.onCleared()
    }
}

fun params(keyword: String, page: Int) = hashMapOf(
    API_KEY to BuildConfig.API_KEY,
    QUERY to keyword,
    OUTPUT to OUTPUT_TYPE_JS,
    MAX_RESULTS to ITEM_PER_PAGE,
    START to page.toString()
)
