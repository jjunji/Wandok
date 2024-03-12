package com.example.wandok.ui.home.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.R
import com.example.wandok.common.LoadState
import com.example.wandok.data.model.Book
import com.example.wandok.ui.core.EditText
import com.example.wandok.ui.core.grayRoundCorner
import com.example.wandok.ui.theme.GrayC1
import com.example.wandok.ui.theme.Orange300
import com.example.wandok.ui.theme.Typography
import timber.log.Timber

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()
    val loadState by viewModel.pageStatus.loadState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val bookList = viewModel.bookList

    val shouldStartPaginate = remember {
        derivedStateOf {
            // 마지막 항목이 현재 화면에 표시 되는지 여부를 나타냄
            val isLastItemDisplayed = (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1) >= listState.layoutInfo.totalItemsCount - 1
            isLastItemDisplayed && viewModel.pageStatus.hasMore
        }
    }

    // paging 조건 만족 시 다음 페이지 호출
    LaunchedEffect(key1 = shouldStartPaginate.value) {
        Timber.tag("test").e("launchedEffect : ${shouldStartPaginate.value}")
        if (shouldStartPaginate.value && (loadState == LoadState.IDLE)) {
            viewModel.requestBookList()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTitle()
        SearchField(
            keyword = keyword,
            onKeywordChanged = { viewModel.onKeywordChanged(it) },
            onSearch = { viewModel.onSearch() }
        )
        BookList(
            bookList = bookList,
            listState = listState
        )
    }
}

@Composable
fun SearchTitle() {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .padding(start = 16.dp, top = 40.dp, bottom = 20.dp)
    ) {
        Box {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .offset(y = (-4).dp)
                    .align(Alignment.BottomCenter)
                    .background(color = Orange300)
            )
            Text(
                style = Typography.subtitle2,
                text = stringResource(id = R.string.search_title),
                color = Color.Black,
                fontSize = 20.sp
            )
        }

        Text(
            style = Typography.subtitle2,
            text = stringResource(id = R.string.search),
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Composable
fun SearchField(
    keyword: String,
    onKeywordChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalAlignment = CenterVertically
    ) {
        Row(
            modifier = Modifier
                .grayRoundCorner()
                .height(40.dp)
                .weight(1f),
            verticalAlignment = CenterVertically
        ) {
            EditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                hint = "도서명",
                text = keyword,
                textSize = 16.sp,
                onValueChange = {
                    onKeywordChanged(it)
                }
            )
        }
        // TODO:
        Image(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(start = 10.dp)
                .clickable {
                    onSearch()
                },
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
        )
    }
}

// TODO: 검색어 입력 마다 리컴포지션 되는 문제
@Composable
fun BookList(
    bookList: List<Book>,
    listState: LazyListState
) {
    Timber.tag("test").e("Recomposition")
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        state = listState
    ) {
        itemsIndexed(
            items = bookList,
            key = { _, book ->
                book.isbn
            }
        ) { index, book ->
            BookRow(
                Modifier.height(150.dp),
                book = book,
                onItemClicked = {
                }
            )

            if (index != bookList.lastIndex) {
                Divider(color = GrayC1, thickness = 1.dp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchTitle() {
    SearchTitle()
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchField() {
    SearchField(keyword = "", onKeywordChanged = {}, onSearch = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewBookList() {
    BookList(
        bookList = listOf(Book("책 제목")),
        listState = LazyListState()
    )
}