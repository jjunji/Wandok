package com.example.wandok.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.BuildConfig
import com.example.wandok.common.constants.KeyValueConstant
import com.example.wandok.common.constants.KeyValueConstant.NAV_ARGS_ISBN
import com.example.wandok.common.extension.onError
import com.example.wandok.common.extension.onSuccess
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.repository.Repository
import com.example.wandok.database.BookEntity
import com.example.wandok.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {
    private val _bookDetail = MutableStateFlow<ResponseState<BookDetail>>(ResponseState.Initial)
    val bookDetail: StateFlow<ResponseState<BookDetail>> = _bookDetail

    private val _addBookDialogState = MutableSharedFlow<AddBookDialogState<BookDetail>>(replay = 0)
    val addBookDialogState: SharedFlow<AddBookDialogState<BookDetail>> = _addBookDialogState

    private val _addComplete = MutableSharedFlow<Boolean>(replay = 0)
    val addComplete: SharedFlow<Boolean> = _addComplete

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
            _bookDetail.emit(ResponseState.Loading)
            delay(1000)
            repository.getBookDetail(queryMap = params(isbn))
                .onSuccess {
                    _bookDetail.emit(ResponseState.Success(it))
                }.onError { _, _ ->
                    _bookDetail.emit(ResponseState.Error(12, ""))
                }
        }
    }

    fun onAddBookClicked() {
        viewModelScope.launch {
            when (val data = bookDetail.value) {
                is ResponseState.Success -> {
                    _addBookDialogState.emit(AddBookDialogState.Show(data.body))
                }

                else -> {
                    _addBookDialogState.emit(AddBookDialogState.Dismiss)
                }
            }
        }
    }

    fun onAddDialogConfirmed(bookDetail: BookDetail) {
        viewModelScope.launch {
            val bookEntity = with(bookDetail) {
                BookEntity(
                    isbn = isbn,
                    title = title,
                    author = author,
                    image = image,
                    publisher = publisher,
                    tableOfContents = tableOfContents
                )
            }
            repository.insertBook(bookEntity)
            _addBookDialogState.emit(AddBookDialogState.Dismiss)
            _addComplete.emit(true)
        }
    }

    fun onAddDialogCanceled() {
        viewModelScope.launch {
            _addBookDialogState.emit(AddBookDialogState.Dismiss)
        }
    }
}

fun params(isbn: String) = hashMapOf(
    KeyValueConstant.API_KEY to BuildConfig.API_KEY,
    KeyValueConstant.ITEM_ID to isbn,
    KeyValueConstant.OUTPUT to KeyValueConstant.OUTPUT_TYPE_JS,
)

sealed class AddBookDialogState<out T> {
    object Dismiss : AddBookDialogState<Nothing>()
    data class Show(val detail: BookDetail) : AddBookDialogState<BookDetail>()
}