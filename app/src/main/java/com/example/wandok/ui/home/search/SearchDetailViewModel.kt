package com.example.wandok.ui.home.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.BuildConfig
import com.example.wandok.common.constants.KeyValueConstant
import com.example.wandok.common.constants.KeyValueConstant.NAV_ARGS_ISBN
import com.example.wandok.common.extension.onError
import com.example.wandok.common.extension.onException
import com.example.wandok.common.extension.onSuccess
import com.example.wandok.common.extension.removeTag
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    init {
        requestBookDetail()
    }

    private fun requestBookDetail() {
        val isbn = savedStateHandle.get<String>(NAV_ARGS_ISBN)
        if (isbn.isNullOrEmpty()) {
            // toast
            return
        }

        viewModelScope.launch {
            repository.getBookDetail(params(isbn))
                .onSuccess {
                    Timber.tag("test").e("${it.tableOfContents}")
                }
                .onError { _, _ -> }
                .onException { }
        }
    }
}

fun params(isbn: String) = hashMapOf(
    KeyValueConstant.API_KEY to BuildConfig.API_KEY,
    KeyValueConstant.ITEM_ID to isbn,
    KeyValueConstant.OUTPUT to KeyValueConstant.OUTPUT_TYPE_JS,
)
