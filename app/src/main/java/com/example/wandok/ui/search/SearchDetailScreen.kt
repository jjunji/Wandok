package com.example.wandok.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.wandok.R
import com.example.wandok.data.model.BookDetail
import com.example.wandok.database.TableOfContent
import com.example.wandok.network.ResponseState
import com.example.wandok.ui.core.Body1Text
import com.example.wandok.ui.core.Body2Text
import com.example.wandok.ui.core.ConfirmCancelDialog
import com.example.wandok.ui.core.CustomAppBar
import com.example.wandok.ui.core.DotsPulsing
import com.example.wandok.ui.theme.GrayC1

@Composable
fun SearchDetailScreen(
    onBackClicked: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel()
) {
    val responseState: ResponseState<BookDetail> by viewModel.bookDetail.collectAsStateWithLifecycle()
    val addBookDialogState by viewModel.addBookDialogState
        .collectAsStateWithLifecycle(initialValue = AddBookDialogState.Dismiss)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomAppBar(modifier = Modifier) { onBackClicked() }
            when (val state = responseState) {
                is ResponseState.Success -> {
                    BookDetailLayout(modifier = Modifier, state.body)
                }

                is ResponseState.Loading -> {
                    LoadingProgress(modifier = Modifier, state = responseState)
                }

                else -> {}
            }
        }
        AddBookButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-20).dp, y = (-20).dp)
        ) {
            viewModel.onAddBookClicked()
        }
    }

    AddBookDialog(
        addBookDialogState,
        confirm = { viewModel.onAddDialogConfirmed(it) },
        cancel = { viewModel.onAddDialogCanceled() }
    ) { onBackClicked() }
}

@Composable
fun BookDetailLayout(modifier: Modifier, item: BookDetail) {
    Column(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_placeholder_book),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .width(220.dp)
                .aspectRatio(1f / 1.4f)
        )
        Spacer(modifier = modifier.height(10.dp))
        BookTitle(modifier = modifier, item.title)
        Spacer(modifier = modifier.height(5.dp))
        Description(
            modifier = modifier
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterHorizontally),
            item.description
        )
        Spacer(modifier = modifier.height(5.dp))

        // 목차
        TableOfContents(
            modifier = modifier,
            itemList = item.tableOfContents
        )
    }
}

@Composable
fun BookTitle(modifier: Modifier, title: String) {
    Column(modifier = modifier.fillMaxWidth()) {
        Body1Text(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = title,
            color = Color.Black
        )
    }
}

@Composable
fun Description(modifier: Modifier, description: String) {
    Body2Text(
        modifier = modifier.padding(horizontal = 10.dp),
        text = description,
        textAlign = TextAlign.Start,
        maxLines = 5
    )
}

@Composable
fun TableOfContents(modifier: Modifier, itemList: List<TableOfContent>) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(
            items = itemList,
        ) { index, item ->
            TableOfContentRow(index, item = item)

            // 아이템 구분선
            Divider(color = GrayC1, thickness = 1.dp)
        }
    }
}

@Composable
fun TableOfContentRow(index: Int, item: TableOfContent) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("${index}.")

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = item.subTitle
        )
    }
}

@Composable
fun LoadingProgress(modifier: Modifier, state: ResponseState<BookDetail>) {
    if (state is ResponseState.Loading) {
        Box(modifier = modifier.fillMaxSize()) {
            DotsPulsing(modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AddBookButton(modifier: Modifier, showDialog: () -> Unit) {
    FloatingActionButton(
        modifier = modifier.size(36.dp),
        shape = CircleShape,
        onClick = {
            showDialog()
        }
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
    }
}

@Composable
fun AddBookDialog(
    state: AddBookDialogState<BookDetail>,
    confirm: (item: BookDetail) -> Unit,
    cancel: () -> Unit,
    complete: () -> Unit
) {
    when (state) {
        is AddBookDialogState.Show -> {
            ConfirmCancelDialog(
                title = stringResource(id = R.string.detail_message_add_book),
                onConfirmClicked = {
                    confirm(state.detail)
                },
                onCancelClicked = {
                    cancel()
                }
            )
        }

        is AddBookDialogState.AddComplete -> {
            complete()
        }

        is AddBookDialogState.Dismiss -> return
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddBookButton() {
    AddBookButton(modifier = Modifier) {}
}