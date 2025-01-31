package com.example.wandok.ui.wandok

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.repository.Repository
import com.example.wandok.ui.wandok.state.LabelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WandokViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _wandokList = MutableStateFlow(emptyList<BookDetail>())
    val wandokList: StateFlow<List<BookDetail>> = _wandokList

    private val _labelState = MutableStateFlow<LabelState<BookDetail>>(LabelState.None)
    val labelState: StateFlow<LabelState<BookDetail>> = _labelState

    init {
        loadWandokList()
    }

    private fun loadWandokList() {
        repository.getWandokList().onEach { list ->
            _wandokList.value = list
            _labelState.value = if (list.isEmpty()) LabelState.None else LabelState.Selected(list.first())
        }.launchIn(viewModelScope)
    }

    fun selectBook(bookDetail: BookDetail) {
        _labelState.value = LabelState.Selected(bookDetail)
    }
}