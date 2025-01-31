package com.example.wandok.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.BuildConfig
import com.example.wandok.common.constants.API_KEY
import com.example.wandok.common.constants.AppConstant.ERR_CD_400
import com.example.wandok.common.constants.AppConstant.REQUEST_DELAY
import com.example.wandok.common.constants.CERT_KEY
import com.example.wandok.common.constants.ISBN
import com.example.wandok.common.constants.ITEM_ID
import com.example.wandok.common.constants.NAV_ARGS_ISBN
import com.example.wandok.common.constants.NAV_ARGS_ISBN13
import com.example.wandok.common.constants.OUTPUT
import com.example.wandok.common.constants.OUTPUT_TYPE_JS
import com.example.wandok.common.constants.PAGE_NO
import com.example.wandok.common.constants.PAGE_SIZE
import com.example.wandok.common.constants.RESULT_STYLE
import com.example.wandok.common.constants.RESULT_STYLE_JSON
import com.example.wandok.common.extension.onError
import com.example.wandok.common.extension.onSuccess
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.local.BookDetailEntity
import com.example.wandok.data.repository.Repository
import com.example.wandok.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
        val isbn13 = savedStateHandle.get<String>(NAV_ARGS_ISBN13)
        if (isbn.isNullOrEmpty()) {
            // toast
            return
        }

        viewModelScope.launch {
            _bookDetail.emit(ResponseState.Loading)
            delay(REQUEST_DELAY)
            repository.getCombinedBookDetail(queryMap = params(isbn), publicQueryMap = publicParams(isbn13 ?: ""))
                .onSuccess {
                    _bookDetail.emit(ResponseState.Success(it))
                }.onError { _, _ ->
                    _bookDetail.emit(ResponseState.Error(ERR_CD_400, ""))
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
                BookDetailEntity(
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
    API_KEY to BuildConfig.API_KEY,
    ITEM_ID to isbn,
    OUTPUT to OUTPUT_TYPE_JS
)

fun publicParams(isbn13: String) = hashMapOf(
    CERT_KEY to BuildConfig.OPEN_API_KEY,
    RESULT_STYLE to RESULT_STYLE_JSON,
    PAGE_NO to "1",
    PAGE_SIZE to "1",
    ISBN to isbn13
)

sealed class AddBookDialogState<out T> {
    data object Dismiss : AddBookDialogState<Nothing>()
    data class Show(val detail: BookDetail) : AddBookDialogState<BookDetail>()
}