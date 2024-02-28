package com.example.wandok.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.repository.Repository
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
        repository.getSearchList(keyword)
    }
}

