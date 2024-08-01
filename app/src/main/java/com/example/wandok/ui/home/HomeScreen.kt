package com.example.wandok.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.wandok.ui.home.filter.BookStatusFilterBottomSheet
import com.example.wandok.ui.home.model.BookStatus
import com.example.wandok.ui.home.model.StatusFilterUiState
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.Typography
import timber.log.Timber

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val statusFilterUiState by viewModel.statusFilterUiState.collectAsStateWithLifecycle()
    val myBookList by viewModel.myBookList.collectAsStateWithLifecycle()

    HomeScreen(
        statusFilterUiState,
        onStatusFilterClicked = viewModel::onStatusFilterClicked,
        onStatusFilterSelected = {
            viewModel.onStatusFilterSelected(it)
        },
        onStatusFilterDismiss = viewModel::onStatusFilterDismiss,
        onSortFilterClicked = {},
        myBookList
    )
}

@Composable
fun HomeScreen(
    statusFilterUiState: StatusFilterUiState,
    onStatusFilterClicked: () -> Unit,
    onStatusFilterSelected: (BookStatus) -> Unit,
    onStatusFilterDismiss: () -> Unit,
    onSortFilterClicked: () -> Unit,
    myBookList: List<BookEntity> = emptyList()
) {
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

            HomeFilter(
                onStatusFilterClicked = { onStatusFilterClicked() },
                onSortFilterClicked = { onSortFilterClicked() }
            )

            MyBookList(
                bookList = myBookList,
                onItemClicked = { Timber.e("onMyBookClicked") }
            )
        }
    }

    if (statusFilterUiState.show) {
        BookStatusFilterBottomSheet(
            selectedFilter = statusFilterUiState.selectedFilter,
            onFilterSelected = { onStatusFilterSelected(it) },
            onDismiss = { onStatusFilterDismiss() }
        )
    }
}

/**
 * 홈 필터 (읽음 상태 필터, 정렬 필터)
 */
@Composable
fun HomeFilter(
    onStatusFilterClicked: () -> Unit,
    onSortFilterClicked: () -> Unit
) {
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
                    .clickable { onStatusFilterClicked() }
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onSortFilterClicked() }
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
//    Home(myBookList = listOf(bookEntity), {}, {})
}