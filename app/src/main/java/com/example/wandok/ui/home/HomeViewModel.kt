package com.example.wandok.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.repository.Repository
import com.example.wandok.ui.home.model.BookStatus
import com.example.wandok.ui.home.model.SortFilterUiState
import com.example.wandok.ui.home.model.SortType
import com.example.wandok.ui.home.model.StatusFilterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _originalBookList = MutableSharedFlow<List<BookDetail>>(replay = 0)
    private val originalBookList = _originalBookList.asSharedFlow()

    private val _myBookList = MutableStateFlow(emptyList<BookDetail>())
    val myBookList = _myBookList

    private val _statusFilterUiState = MutableStateFlow(StatusFilterUiState())
    val statusFilterUiState = _statusFilterUiState

    private val _sortFilterUiState = MutableStateFlow(SortFilterUiState())
    val sortFilterUiState = _sortFilterUiState

    init {
        viewModelScope.launch {
            async {
                loadMyBookList()
            }.await()

            combine(originalBookList, statusFilterUiState, sortFilterUiState) { bookList, status, sort ->
                Triple(bookList, status.selectedFilter, sort.selectedFilter)
            }
                .distinctUntilChanged()
                .collect { (bookList, statusFilter, sortFilter) ->
                    combineFilterState(bookList, statusFilter, sortFilter)
                }
        }
    }

    // TODO: 책 추가 후 로드 되는 과정 로그 확인
    private fun loadMyBookList() {
        viewModelScope.launch {
            repository.getAllMyBook().collectLatest {
                _originalBookList.emit(it)
            }
        }
    }

    // 필터 상태 조합
    private fun combineFilterState(
        originalBookList: List<BookDetail>,
        bookStatus: BookStatus,
        sortType: SortType
    ) {
        _myBookList.value = originalBookList
            .filter { bookStatus.filter(it) }
            .sortedWith(sortType.comparator)
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