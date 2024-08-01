package com.example.wandok.ui.home.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.ui.core.Body1Text
import com.example.wandok.ui.core.Body2Text
import com.example.wandok.ui.core.FilterItem
import com.example.wandok.ui.home.model.BookStatus
import com.example.wandok.ui.theme.Orange500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookStatusFilterBottomSheet(
    selectedFilter: BookStatus,
    onFilterSelected: (BookStatus) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Body1Text(
                modifier = Modifier.padding(start = 16.dp, bottom = 20.dp),
                text = "독서 상태",
                bold = true
            )

            MyBookFilters(
                modifier = Modifier.padding(start = 16.dp, bottom = 45.dp),
                bookStatus = selectedFilter,
                onFilterSelected
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ResetButton()
                ApplyButton()
            }
        }
    }
}

@Composable
fun MyBookFilters(
    modifier: Modifier,
    bookStatus: BookStatus,
    onSelect: (bookStatus: BookStatus) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row {
            FilterItem(
                text = "모든 책",
                isSelected = BookStatus.All == bookStatus
            ) {
                onSelect(BookStatus.All)
            }
            FilterItem(
                modifier = Modifier.padding(start = 10.dp),
                text = "읽는 중",
                isSelected = BookStatus.Reading == bookStatus
            ) { onSelect(BookStatus.Reading) }
            FilterItem(
                modifier = Modifier.padding(start = 10.dp),
                text = "독서 예정",
                isSelected = BookStatus.ToRead == bookStatus
            ) { onSelect(BookStatus.ToRead) }
        }
        FilterItem(
            modifier = Modifier.padding(top = 10.dp),
            text = "다 읽은 책", isSelected = BookStatus.Done == bookStatus
        ) { onSelect(BookStatus.Done) }
    }
}

@Composable
fun ResetButton() {
    Row(
        modifier = Modifier
            .width(80.dp)
            .height(50.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.padding(end = 5.dp),
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = null
        )
        Body2Text(text = stringResource(id = R.string.common_reset))
    }
}

@Composable
fun ApplyButton() {
    val shape = RoundedCornerShape(22.dp)
    Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .size(width = 238.dp, height = 44.dp)
            .background(color = Orange500, shape = shape)
            .clip(shape)
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        Body2Text(text = "적용", color = Color.White, bold = true)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterBottomSheet() {
}