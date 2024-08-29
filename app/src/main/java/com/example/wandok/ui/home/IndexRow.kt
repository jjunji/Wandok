package com.example.wandok.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wandok.data.model.local.TableOfContent

@Composable
fun IndexRow(tableOfContent: TableOfContent) {
    Row(
        modifier = Modifier
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("${tableOfContent.index}.")

        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = tableOfContent.subTitle
        )
    }
}