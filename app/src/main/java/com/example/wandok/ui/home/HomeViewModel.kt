package com.example.wandok.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.repository.Repository
import com.example.wandok.database.BookEntity
import com.example.wandok.ui.home.model.BookStatus
import com.example.wandok.ui.home.model.SortFilterUiState
import com.example.wandok.ui.home.model.SortType
import com.example.wandok.ui.home.model.StatusFilterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _myBookList = MutableStateFlow(emptyList<BookEntity>())
    val myBookList = _myBookList

    private val _statusFilterUiState = MutableStateFlow(StatusFilterUiState())
    val statusFilterUiState = _statusFilterUiState

    private val _sortFilterUiState = MutableStateFlow(SortFilterUiState())
    val sortFilterUiState = _sortFilterUiState

    init {
        loadMyBookList()
    }

    private fun loadMyBookList() {
        viewModelScope.launch {
            repository.getAllMyBook().collectLatest {
                _myBookList.emit(it)
            }
        }
    }

    // 읽음 상태 필터 클릭
    fun onStatusFilterClicked() {
        _statusFilterUiState.value = _statusFilterUiState.value.copy(
            show = true
        )
    }

    // 필터 항목 선택 됨
    fun onStatusFilterSelected(filter: BookStatus) {
        _statusFilterUiState.value = _statusFilterUiState.value.copy(
            show = false,
            selectedFilter = filter
        )
    }

    fun onStatusFilterDismiss() {
        _statusFilterUiState.value = _statusFilterUiState.value.copy(
            show = false
        )
    }

    // 정렬 필터 클릭
    fun onSortFilterClicked() {
        _sortFilterUiState.value = _sortFilterUiState.value.copy(
            show = true
        )
    }

    // 정렬 필터 선택됨
    fun onSortFilterSelected(filter: SortType) {
        _sortFilterUiState.value = _sortFilterUiState.value.copy(
            show = false,
            selectedFilter = filter
        )
    }

    fun onSortFilterDismiss() {
        _sortFilterUiState.value = _sortFilterUiState.value.copy(
            show = false
        )
    }
}