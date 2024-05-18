package com.example.wandok.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.repository.Repository
import com.example.wandok.database.BookEntity
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
}