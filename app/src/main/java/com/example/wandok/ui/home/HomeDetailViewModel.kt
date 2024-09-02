package com.example.wandok.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.common.constants.KeyValueConstant.NAV_ARGS_ISBN
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.local.TableOfContent
import com.example.wandok.data.model.mapper.BookDetailMapper.mapToEntity
import com.example.wandok.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
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

    @Suppress("MagicNumber")
    fun updateBookStatus(myBook: BookDetail, clickedItem: TableOfContent) {
        val updateContents = myBook.tableOfContents.map {
            if (it.index == clickedItem.index) {
                it.copy(read = !clickedItem.read)
            } else {
                it
            }
        }

        val updateProgress = (updateContents.count { it.read } / updateContents.size) * 100

        val entity = myBook.copy(
            progress = updateProgress,
            tableOfContents = updateContents
        ).mapToEntity()

        viewModelScope.launch {
            repository.updateMyBookStatus(entity)
        }
    }
}