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
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.wandok.ui.theme.Orange500
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookStatusFilterBottomSheet(
    showBottomSheet: Boolean,
    sheetState: SheetState,
    scope: CoroutineScope,
    onDismiss: () -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

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
                Body1Text(
                    modifier = Modifier.padding(start = 16.dp, bottom = 20.dp),
                    text = "독서 상태",
                    bold = true
                )

                MyBookFilterItem(
                    modifier = Modifier.padding(start = 16.dp, bottom = 45.dp),
                    isSelected,
                    onSelect = { isSelected = !isSelected }
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
}

@Composable
fun MyBookFilterItem(
    modifier: Modifier,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row {
            FilterItem(text = "모든 책", isSelected = isSelected) { onSelect() }
            FilterItem(
                modifier = Modifier.padding(start = 10.dp),
                text = "읽는 중",
                isSelected = isSelected
            ) { onSelect() }
            FilterItem(
                modifier = Modifier.padding(start = 10.dp),
                text = "독서 예정",
                isSelected = isSelected
            ) { onSelect() }
        }
        FilterItem(
            modifier = Modifier.padding(top = 10.dp),
            text = "다 읽은 책", isSelected = isSelected
        ) { onSelect() }
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewFilterBottomSheet() {
    val sheetState = rememberStandardBottomSheetState(SheetValue.Expanded)
    val scope = rememberCoroutineScope()
    BookStatusFilterBottomSheet(
        showBottomSheet = true,
        sheetState = sheetState,
        scope = scope
    ) {}
}