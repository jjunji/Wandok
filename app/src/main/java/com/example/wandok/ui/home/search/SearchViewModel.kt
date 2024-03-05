package com.example.wandok.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.BuildConfig
import com.example.wandok.common.constants.KeyValueConstant.API_KEY
import com.example.wandok.common.constants.KeyValueConstant.ITEM_PER_PAGE
import com.example.wandok.common.constants.KeyValueConstant.MAX_RESULTS
import com.example.wandok.common.constants.KeyValueConstant.OUTPUT
import com.example.wandok.common.constants.KeyValueConstant.OUTPUT_TYPE_JS
import com.example.wandok.common.constants.KeyValueConstant.QUERY
import com.example.wandok.common.constants.KeyValueConstant.START
import com.example.wandok.data.repository.Repository
import com.example.wandok.network.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var keyword = MutableStateFlow("")

    fun onKeywordChanged(value: String) {
        viewModelScope.launch {
            keyword.emit(value)
        }
    }

    fun onSearch(keyword: String) {
        viewModelScope.launch {
            when(val result = repository.getBookList(params(keyword))) {
                is ResultState.Success -> {
                    Timber.tag("tt").e("${result.body}")
                }
                else -> {
                    Timber.e("error")
                }
            }
        }
    }
}

fun params(keyword: String) = hashMapOf(
    API_KEY to BuildConfig.API_KEY,
    QUERY to keyword,
    OUTPUT to OUTPUT_TYPE_JS,
    MAX_RESULTS to ITEM_PER_PAGE,
    START to "1"
)
