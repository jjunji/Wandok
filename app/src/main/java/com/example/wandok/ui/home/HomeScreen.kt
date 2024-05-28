package com.example.wandok.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.R
import com.example.wandok.database.BookEntity
import com.example.wandok.ui.core.Body1Text
import com.example.wandok.ui.core.Body2Text
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.GrayC1
import com.example.wandok.ui.theme.LightGray
import com.example.wandok.ui.theme.Typography
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val myBookList by viewModel.myBookList.collectAsStateWithLifecycle()

    Home(myBookList = myBookList)
}

@Composable
fun Home(myBookList: List<BookEntity>) {
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

            HomeFilter()
            MyBookList(
                bookList = myBookList,
                onItemClicked = { Timber.e("onMyBookClicked") }
            )
        }
    }
}

@Composable
fun HomeFilter() {
    Box(modifier = Modifier
        .wrapContentSize()
        .padding(top = 20.dp, start = 16.dp)
    ) {
        FilterWithBottomSheet()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .clickable {  }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterWithBottomSheet() {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Bottom Sheet Content", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Here is some more content.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }) {
                    Text("Close")
                }
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier.clickable {
                    Timber.e("filter Clicked")
                    scope.launch {
                        bottomSheetState.show()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val bookEntity = BookEntity("", "Title", "", "", "")
    Home(myBookList = listOf(bookEntity))
}