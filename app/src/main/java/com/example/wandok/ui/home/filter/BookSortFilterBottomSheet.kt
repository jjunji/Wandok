package com.example.wandok.ui.home.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.ui.core.Body1Text
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSortFilterBottomSheet(
    showBottomSheet: Boolean,
    sheetState: SheetState,
    scope: CoroutineScope,
    onDismiss: () -> Unit
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onDismiss() }
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                SortByNewestRow()
                SortByOldestRow()
                SortByHighestProgressRow()
                SortByLowestProgressRow()
            }
        }
    }
}

@Composable
fun SortByNewestRow() {
    Box(
        modifier = Modifier
            .clickable {

            }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        Body1Text(
            text = stringResource(id = R.string.filter_newest),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Image(
            painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun SortByOldestRow() {
    Box(
        modifier = Modifier
            .clickable {

            }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        Body1Text(
            text = stringResource(id = R.string.filter_oldest),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Image(
            painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun SortByHighestProgressRow() {
    Box(
        modifier = Modifier
            .clickable {

            }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        Body1Text(
            text = stringResource(id = R.string.filter_highest_progress),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Image(
            painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun SortByLowestProgressRow() {
    Box(
        modifier = Modifier
            .clickable {

            }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        Body1Text(
            text = stringResource(id = R.string.filter_lowest_progress),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Image(
            painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewBookSortFilterBottomSheet() {
    val sheetState = rememberStandardBottomSheetState(SheetValue.Expanded)
    val scope = rememberCoroutineScope()
    BookSortFilterBottomSheet(
        showBottomSheet = true,
        sheetState = sheetState,
        scope = scope
    ) {}
}