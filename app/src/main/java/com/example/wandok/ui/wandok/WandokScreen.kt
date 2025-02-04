package com.example.wandok.ui.wandok

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.data.model.BookDetail
import com.example.wandok.ui.core.shadowExcludeLeft
import com.example.wandok.ui.wandok.state.LabelState

val cornerRadius = 16.dp
val wandokItemWidth = 110.dp
val wandokItemHeight = 150.dp
val animYOffset = 40.dp
val firstItemBottomPadding = 20.dp
val shape = RoundedCornerShape(topEnd = cornerRadius, bottomEnd = cornerRadius)

@Composable
fun WandokRoute(
    paddingValues: PaddingValues,
    viewModel: WandokViewModel = hiltViewModel()
) {
    val wandokList by viewModel.wandokList.collectAsStateWithLifecycle()
    val labelState by viewModel.labelState.collectAsStateWithLifecycle()

    WandokScreen(
        paddingValues,
        labelState,
        wandokList,
        onItemClicked = {
            viewModel.selectBook(it)
        }
    )
}

@Composable
fun WandokScreen(
    paddingValues: PaddingValues,
    labelState: LabelState<BookDetail>,
    wandokList: List<BookDetail>,
    onItemClicked: (bookDetail: BookDetail) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ExcludeLeftShadowContainer {
            Label(labelState, wandokList.size)
        }

        val rowHeight = wandokItemHeight + animYOffset + firstItemBottomPadding
        HorizontalWandokList(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(rowHeight),
            items = wandokList,
            onItemClicked = { onItemClicked(it) }
        )
    }
}

@Composable
fun ExcludeLeftShadowContainer(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 45.dp)
            .wrapContentSize()
            .shadowExcludeLeft(
                borderRadius = cornerRadius, blurRadius = cornerRadius
            )
    ) { content() }
}