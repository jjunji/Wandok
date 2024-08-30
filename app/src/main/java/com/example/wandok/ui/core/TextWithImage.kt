package com.example.wandok.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wandok.R

@Composable
fun TextWithImage(
    modifier: Modifier = Modifier,
    title: String,
    iconAttr: IconAttr
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconAttr.placeDirection == IconDirection.LEFT) {
            Image(painter = iconAttr.painter, contentDescription = "")
            Spacer(modifier = Modifier.width(iconAttr.space))
        }

        BodyMediumText(text = title)

        if (iconAttr.placeDirection == IconDirection.RIGHT) {
            Spacer(modifier = Modifier.width(iconAttr.space))
            Image(painter = iconAttr.painter, contentDescription = "")
        }
    }
}

data class IconAttr(
    val painter: Painter,
    val placeDirection: IconDirection = IconDirection.RIGHT,
    val space: Dp = 0.dp
)

enum class IconDirection {
    LEFT, RIGHT
}

@Preview(showBackground = true)
@Composable
fun PreviewTextWithImage() {
    val iconAttr = IconAttr(
        placeDirection = IconDirection.LEFT,
        painter = painterResource(id = R.drawable.ic_check_done),
        space = 3.dp
    )
    TextWithImage(title = "타이틀", iconAttr = iconAttr)
}