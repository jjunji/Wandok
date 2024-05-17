package com.example.wandok.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.database.BookEntity
import com.example.wandok.ui.core.shadow
import com.example.wandok.ui.search.BookImage

val shadowColor = Color(0x4D000000)

@Composable
fun MyBookRow(myBook: BookEntity, onItemClicked: () -> Unit) {

    // Shadow Container
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp)
            .shadow(
                shadowColor,
                borderRadius = 1.dp,
                offsetX = 7.dp,
                offsetY = 7.dp,
                spread = 7.dp,
                blurRadius = 10.dp
            )
            .clickable { onItemClicked() }
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .clip(RoundedCornerShape(1.dp))
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(1.dp))
                .background(Color.White)
        ) {
            Row(modifier = Modifier.height(150.dp)) {
                Column {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 12.dp, top = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.widthIn(100.dp, 340.dp),
                            text = myBook.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        BookImage(
                            imageUrl = myBook.image,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}