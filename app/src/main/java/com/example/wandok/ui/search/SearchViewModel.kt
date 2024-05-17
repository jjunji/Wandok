package com.example.wandok.ui.search

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
import com.example.wandok.data.model.response.Book
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var keyword = MutableStateFlow("")                  // 실시간 타이핑 키워드
    private val searchedKeyword = MutableStateFlow("")  // 실제 검색한 키워드
    val pageStatus = PageStatus<Book>()
    var bookList = pageStatus.items
    val refreshing: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)

    fun onKeywordChanged(value: String) {
        viewModelScope.launch {
            keyword.emit(value)
        }
    }

    fun onSearch(keyword: String) {
        viewModelScope.launch {
            searchedKeyword.emit(keyword)
        }
        pageStatus.init()
        requestBookList(true)
    }

    fun requestBookList(newRequest: Boolean = false) {
        if (pageStatus.loadState.value == LoadState.LOADING) return
        if (newRequest) pageStatus.init()

        val params = params(searchedKeyword.value, pageStatus.currentPage + 1)

        viewModelScope.launch {
            pageStatus.setLoadState(LoadState.LOADING)
            repository.getMyBookList(params)
                .onSuccess {
                    pageStatus.notifyPageStatusChanged(it.items, it.countOfAllItems, it.page)
                }
                .onError { _, message ->
                    // toast message
                }
                .onException {}
            pageStatus.setLoadState(LoadState.IDLE)
            refreshing.emit(false)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            refreshing.emit(true)
        }
        requestBookList(true)
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
