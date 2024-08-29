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
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.ui.core.BodyLargeText
import com.example.wandok.ui.home.model.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSortFilterBottomSheet(
    sheetState: SheetState = rememberStandardBottomSheetState(skipHiddenState = false),
    selectedFilter: SortType,
    onFilterApply: (SortType) -> Unit,
    onDismiss: () -> Unit,
) {
    val filterNow by remember { mutableStateOf(selectedFilter) }

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
            SortByNewestRow(filterNow, onFilterApply = { onFilterApply(SortType.Newest) })
            SortByOldestRow(filterNow, onFilterApply = { onFilterApply(SortType.Oldest) })
            SortByHighestProgressRow(filterNow, onFilterApply = { onFilterApply(SortType.HighestProgress) })
            SortByLowestProgressRow(filterNow, onFilterApply = { onFilterApply(SortType.LowestProgress) })
        }
    }
}

@Composable
fun SortByNewestRow(
    filterNow: SortType,
    onFilterApply: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onFilterApply() }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        BodyLargeText(
            text = stringResource(id = R.string.filter_newest),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        if (SortType.Newest == filterNow) {
            Image(
                painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun SortByOldestRow(
    filterNow: SortType,
    onFilterApply: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onFilterApply() }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        BodyLargeText(
            text = stringResource(id = R.string.filter_oldest),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        if (SortType.Oldest == filterNow) {
            Image(
                painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun SortByHighestProgressRow(
    filterNow: SortType,
    onFilterApply: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onFilterApply() }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        BodyLargeText(
            text = stringResource(id = R.string.filter_highest_progress),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        if (SortType.HighestProgress == filterNow) {
            Image(
                painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun SortByLowestProgressRow(
    filterNow: SortType,
    onFilterApply: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onFilterApply() }
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 20.dp)
    ) {
        BodyLargeText(
            text = stringResource(id = R.string.filter_lowest_progress),
            bold = true,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        if (SortType.LowestProgress == filterNow) {
            Image(
                painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewBookSortFilterBottomSheet() {
    BookSortFilterBottomSheet(
        selectedFilter = SortType.Newest,
        onFilterApply = {},
        onDismiss = {}
    )
}