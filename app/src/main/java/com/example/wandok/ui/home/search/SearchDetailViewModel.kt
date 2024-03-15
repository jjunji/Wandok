package com.example.wandok.ui.home.search

import androidx.lifecycle.ViewModel
import com.example.wandok.BuildConfig
import com.example.wandok.common.constants.KeyValueConstant
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        requestBookDetail()
    }

    private fun requestBookDetail() {

    }
}

fun params(isbn: String) = hashMapOf(
    KeyValueConstant.API_KEY to BuildConfig.API_KEY,
    KeyValueConstant.ITEM_ID to isbn,
    KeyValueConstant.OUTPUT to KeyValueConstant.OUTPUT_TYPE_JS,
)
