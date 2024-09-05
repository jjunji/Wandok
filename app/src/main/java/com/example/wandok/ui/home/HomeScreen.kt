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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.R
import com.example.wandok.data.model.BookDetail
import com.example.wandok.ui.home.filter.BookSortFilterBottomSheet
import com.example.wandok.ui.home.filter.BookStatusFilterBottomSheet
import com.example.wandok.ui.home.model.BookStatus
import com.example.wandok.ui.home.model.SortFilterUiState
import com.example.wandok.ui.home.model.SortType
import com.example.wandok.ui.home.model.StatusFilterUiState
import com.example.wandok.ui.theme.DarkGray
import com.example.wandok.ui.theme.Typography

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    onItemClicked: (isbn: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val statusFilterUiState by viewModel.statusFilterUiState.collectAsStateWithLifecycle()
    val sortFilterUiState by viewModel.sortFilterUiState.collectAsStateWithLifecycle()
    val myBookList by viewModel.myBookList.collectAsStateWithLifecycle()

    // TODO: 책 추가 후 로드 되는 과정 로그 확인
    HomeScreen(
        paddingValues,
        statusFilterUiState,
        sortFilterUiState,
        onStatusFilterClicked = viewModel::onStatusFilterClicked,
        onSortFilterClicked = viewModel::onSortFilterClicked,
        onStatusFilterSelected = {
            viewModel.onStatusFilterSelected(it)
        },
        onStatusFilterDismiss = viewModel::onStatusFilterDismiss,
        onSortFilterSelected = {
            viewModel.onSortFilterSelected(it)
        },
        onSortFilterDismiss = viewModel::onSortFilterDismiss,
        myBookList,
        onItemClicked = { onItemClicked(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    padding: PaddingValues,
    statusFilterUiState: StatusFilterUiState,
    sortFilterUiState: SortFilterUiState,
    onStatusFilterClicked: () -> Unit,
    onSortFilterClicked: () -> Unit,
    onStatusFilterSelected: (BookStatus) -> Unit,
    onStatusFilterDismiss: () -> Unit,
    onSortFilterSelected: (SortType) -> Unit,
    onSortFilterDismiss: () -> Unit,
    myBookList: List<BookDetail> = emptyList(),
    onItemClicked: (isbn: String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(top = 40.dp, start = 16.dp),
                text = "지훈님이\n읽고 있는 책",
                style = Typography.titleLarge
            )

            // 상태 필터, 정렬 필터 영역
            HomeFilter(
                sortType = sortFilterUiState.selectedFilter,
                onStatusFilterClicked = { onStatusFilterClicked() },
                onSortFilterClicked = { onSortFilterClicked() }
            )

            // 추가한 책 리스트
            MyBookList(
                bookList = myBookList,
                onItemClicked = { onItemClicked(it) }
            )
        }
    }

    if (statusFilterUiState.show) {
        BookStatusFilterBottomSheet(
            selectedFilter = statusFilterUiState.selectedFilter,
            onFilterApply = { onStatusFilterSelected(it) },
            onDismiss = { onStatusFilterDismiss() }
        )
    }

    if (sortFilterUiState.show) {
        BookSortFilterBottomSheet(
            selectedFilter = sortFilterUiState.selectedFilter,
            onFilterApply = { onSortFilterSelected(it) },
            onDismiss = { onSortFilterDismiss() }
        )
    }
}

/**
 * 홈 필터 (읽음 상태 필터, 정렬 필터)
 */
@Composable
fun HomeFilter(
    sortType: SortType,
    onStatusFilterClicked: () -> Unit,
    onSortFilterClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onStatusFilterClicked() }
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onSortFilterClicked() }
            ) {
                Text(
                    text = sortType.title,
                    color = DarkGray,
                    fontSize = 14.sp,
                    style = Typography.bodyMedium,
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
    bookList: List<BookDetail>,
    onItemClicked: (isbn: String) -> Unit
) {
//    Timber.tag("MyBookList").e("Recomposition") // TODO: 로딩(검색) 동안 recomposition
    LazyColumn(
        contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(
            items = bookList,
        ) { _, book ->
            MyBookRow(
                myBook = book,
                onItemClicked = {
                    onItemClicked(book.isbn)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    MyBookList(bookList = listOf(BookDetail("1234"))) {}
}