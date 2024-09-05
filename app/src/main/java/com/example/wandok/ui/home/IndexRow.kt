package com.example.wandok.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.data.model.local.TableOfContent
import com.example.wandok.ui.core.BodyMediumText
import com.example.wandok.ui.core.IconAttr
import com.example.wandok.ui.core.IconDirection
import com.example.wandok.ui.core.TextWithImage
import com.example.wandok.ui.theme.LightGray
import com.example.wandok.ui.theme.WhiteGray
import timber.log.Timber

@Composable
fun IndexRow(
    tableOfContent: TableOfContent,
    onItemClicked: (item: TableOfContent) -> Unit
) {
    Timber.tag("home").e("IndexRow()")
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyMediumText(
                text = "${tableOfContent.index + 1}."
            )

            BodyMediumText(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                textAlign = TextAlign.Start,
                text = tableOfContent.subTitle,
                color = if (tableOfContent.read) {
                    LightGray
                } else {
                    Color.Black
                }
            )

            val iconAttr = IconAttr(
                placeDirection = IconDirection.LEFT,
                space = 3.dp,
                painter = if (tableOfContent.read) {
                    painterResource(id = R.drawable.ic_check_done)
                } else {
                    painterResource(id = R.drawable.ic_check_dimmed)
                }
            )
            TextWithImage(
                modifier = Modifier
                    .padding(start = 10.dp, top = 3.dp, bottom = 3.dp)
                    .clickable { onItemClicked(tableOfContent) },
                title = "읽음",
                textColor = if (tableOfContent.read) {
                    LightGray
                } else {
                    Color.Black
                },
                iconAttr = iconAttr
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.5.dp,
            color = WhiteGray
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewIndexRow() {
    IndexRow(tableOfContent = TableOfContent(1, "타이틀", true)) {}
}