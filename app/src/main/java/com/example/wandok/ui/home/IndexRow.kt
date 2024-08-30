package com.example.wandok.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.wandok.ui.theme.WhiteGray

@Composable
fun IndexRow(tableOfContent: TableOfContent) {
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
                text = tableOfContent.subTitle
            )

            val iconAttr = IconAttr(
                placeDirection = IconDirection.LEFT,
                painter = painterResource(id = R.drawable.ic_check_done),
                space = 3.dp
            )
            TextWithImage(
                modifier = Modifier.padding(start = 10.dp, top = 3.dp, bottom = 3.dp),
                title = "읽음",
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
    IndexRow(tableOfContent = TableOfContent(1, "타이틀", true))
}