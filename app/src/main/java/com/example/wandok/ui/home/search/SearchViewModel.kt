package com.example.wandok.ui.home.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.BuildConfig
import com.example.wandok.common.ListState
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
import com.example.wandok.network.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var keyword = MutableStateFlow("")
    val pageStatus = PageStatus<Book>()
//    private val _bookList: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val bookList = pageStatus.items

    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)


    fun onKeywordChanged(value: String) {
        viewModelScope.launch {
            keyword.emit(value)
        }
    }

    fun onSearch(keyword: String) {
        viewModelScope.launch {
            repository.getBookList(params(keyword))
                .onSuccess {
                    canPaginate = true
                    pageStatus.notifyPageStatusChanged(it.items, it.countOfItems)

                    listState = ListState.IDLE

                    if (canPaginate) page++
                }
                .onError { _, _ ->  }
                .onException {  }
        }
    }

    fun requestNextPage() {
        Timber.tag("test").e("requestNextPage ==== ")
    }

    override fun onCleared() {
        // TODO:
        pageStatus.init()
        listState = ListState.IDLE
        canPaginate = false
        super.onCleared()
    }
}

fun params(keyword: String) = hashMapOf(
    API_KEY to BuildConfig.API_KEY,
    QUERY to keyword,
    OUTPUT to OUTPUT_TYPE_JS,
    MAX_RESULTS to ITEM_PER_PAGE,
    START to "1"
)
