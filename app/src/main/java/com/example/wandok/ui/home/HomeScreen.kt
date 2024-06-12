package com.example.wandok.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.R
import com.example.wandok.database.BookEntity
import com.example.wandok.ui.core.FilterItem
import com.example.wandok.ui.home.filter.FilterBottomSheet
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val myBookList by viewModel.myBookList.collectAsStateWithLifecycle()

    Home(
        myBookList = myBookList,
        onFilterClicked = {
            showBottomSheet = true
        }
    )

    FilterBottomSheet(
        showBottomSheet = showBottomSheet,
        sheetState = sheetState,
        scope = scope,
        onDismiss = {
            showBottomSheet = false
        }
    )
}

@Composable
fun Home(myBookList: List<BookEntity>, onFilterClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(top = 40.dp, start = 16.dp),
                text = "지훈님이\n읽고 있는 책",
                style = Typography.h6
            )

            HomeFilter(onFilterClicked = { onFilterClicked() })

            MyBookList(
                bookList = myBookList,
                onItemClicked = { Timber.e("onMyBookClicked") }
            )
        }
    }
}

@Composable
fun HomeFilter(onFilterClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onFilterClicked()
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "등록순",
                    color = DarkGray,
                    fontSize = 14.sp,
                    style = Typography.body2,
                    modifier = Modifier
                        .padding(end = 6.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
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

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val bookEntity = BookEntity("", "Title", "", "", "")
    Home(myBookList = listOf(bookEntity)) {}
}