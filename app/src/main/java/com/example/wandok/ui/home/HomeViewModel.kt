package com.example.wandok.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wandok.data.repository.Repository
import com.example.wandok.database.BookEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _myBookList = MutableStateFlow(emptyList<BookEntity>())
    val myBookList = _myBookList

//    val myBookList: StateFlow<List<BookEntity>>
//        get() = repository.getAllMyBook()
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = emptyList()
//            )

//    viewModelScope.launch {
//        repository
//        dao.getUsers().collectLatest {
//            userList.value = it
//        }
//    }

    init {
        viewModelScope.launch {
            repository.getAllMyBook().collectLatest {
                _myBookList.emit(it)
            }
        }
    }
}