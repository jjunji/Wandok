package com.example.wandok.ui.home.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wandok.R
import com.example.wandok.ui.core.EditText
import com.example.wandok.ui.core.grayRoundCorner
import com.example.wandok.ui.theme.Orange300
import com.example.wandok.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val keyword by viewModel.keyword.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTitle()
        SearchField(
            keyword = keyword,
            onKeywordChanged = {
                viewModel.onKeywordChanged(it)
            },
            onSearch =  {
                viewModel.onSearch(it)
            }
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
    onSearch: (keyword: String) -> Unit
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
                    onSearch(keyword)
                },
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "",
        )
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