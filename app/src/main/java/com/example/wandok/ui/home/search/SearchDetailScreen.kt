package com.example.wandok.ui.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.R
import com.example.wandok.data.model.dao.BookDetail
import com.example.wandok.network.ResponseState
import com.example.wandok.ui.core.CustomAppBar
import com.example.wandok.ui.core.DotsPulsing
import timber.log.Timber

@Composable
fun SearchDetailScreen(
    onBackClicked: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel()
) {
    val state: ResponseState<BookDetail> by viewModel.bookDetail.collectAsStateWithLifecycle()

//     when(val bookDetailState = state) {
//         is ResponseState.Success -> {
//             Timber.tag("test").e("${bookDetailState.body}")
//         }
//         is ResponseState.Loading -> {
//             Timber.tag("test").e("loading~!!!!!!")
//         }
//         else -> {
//             Timber.tag("test").e("asdfasfdafsaf")
//         }
//     }

//    Box(modifier = Modifier.fillMaxSize()) {
//        CustomAppBar(modifier = Modifier.align(Alignment.TopCenter)) { onBackClicked() }
//        AddBookButton(modifier = Modifier
//            .align(Alignment.BottomEnd)
//            .offset(x = (-20).dp, y = (-20).dp)
//        )
//        LoadingProgress(modifier = Modifier.align(Alignment.Center), state = state)
//        Column() {
//
//        }
//    }

//    a(onBackClicked = { /*TODO*/ }, state = state)
    LoadingProgress(modifier = Modifier.fillMaxSize(), state = state)
}

@Composable
fun a(onBackClicked: () -> Unit, state: ResponseState<BookDetail>) {
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            CustomAppBar { onBackClicked() }
//            Content()
//        }
//        LoadingProgress(modifier = Modifier.align(Alignment.Center), state = state)
//        AddBookButton(modifier = Modifier.align(Alignment.BottomEnd))
//    }
}

@Composable
fun Content() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "test"
        )
    }
}

@Composable
fun LoadingProgress(modifier: Modifier, state: ResponseState<BookDetail>) {
    Timber.tag("test").e("$state")
    if (state is ResponseState.Loading) {
        Box(
            modifier = modifier
                .wrapContentWidth()
                .background(color = Color.Black)
        ) {
            Timber.tag("test").e("dotsPulsing")
            DotsPulsing()
        }
    }
}

@Composable
fun AddBookButton(modifier: Modifier) {
    FloatingActionButton(
        modifier = modifier.size(36.dp),
        shape = CircleShape,
        onClick = { /*TODO*/ }
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddBookButton() {
    AddBookButton(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    a(onBackClicked = { /*TODO*/ }, state = ResponseState.Loading)
}