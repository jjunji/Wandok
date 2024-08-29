package com.example.wandok.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.common.constants.KeyValueConstant.NAV_ARGS_ISBN
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {
    private val _myBook = MutableSharedFlow<BookDetail?>(replay = 0)
    val myBook = _myBook.asSharedFlow()

    init {
        val isbn: String? = savedStateHandle[NAV_ARGS_ISBN]
        isbn?.let {
            viewModelScope.launch {
                repository.getMyBook(isbn).collectLatest {
                    _myBook.emit(it)
                }
            }
        }
    }
}