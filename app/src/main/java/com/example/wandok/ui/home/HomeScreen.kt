package com.example.wandok.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.database.BookEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun HomeScreen(
    showReadingProgress: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val myBookList by viewModel.myBookList.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MyBookList(
            bookList = myBookList,
            onItemClicked = { Timber.e("onMyBookClicked") })
    }
}

@Composable
fun MyBookList(
    bookList: List<BookEntity>,
    onItemClicked: (isbn: String) -> Unit
) {
    Timber.tag("test").e("Recomposition $bookList")
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(
            items = bookList,
            key = { _, book ->
                book.isbn
            }
        ) { index, book ->
            MyBookRow(
                myBook = book,
                onItemClicked = { onItemClicked(book.isbn) }
            )
        }
    }
}