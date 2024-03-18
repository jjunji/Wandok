package com.example.wandok.ui.home.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wandok.R
import com.example.wandok.ui.core.CustomAppBar

@Composable
fun SearchDetailScreen(
    onBackClicked: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomAppBar(modifier = Modifier.align(Alignment.TopCenter)) { onBackClicked() }
        AddBookButton(modifier = Modifier
            .align(Alignment.BottomEnd)
            .offset(x = (-20).dp, y = (-20).dp)
        )
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