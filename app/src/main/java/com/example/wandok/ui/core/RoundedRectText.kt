package com.example.wandok.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.R
import com.example.wandok.ui.theme.DarkGray

@Composable
fun RoundedRectText(
    modifier: Modifier = Modifier,
    text: String,
    cornerRadius: Dp,
    iconAttr: IconAttr? = null,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = DarkGray,
                    cornerRadius = CornerRadius(cornerRadius.toPx()),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
            .clickable { onClick() }
    ) {
        if (iconAttr == null) {
            BodySmallText(
                text = text,
                bold = true,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
            )
        } else {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (iconAttr.placeDirection == IconDirection.LEFT) {
                    Image(painter = painterResource(id = R.drawable.ic_edit_goal), contentDescription = "")
                    Spacer(modifier = Modifier.width(5.dp))
                }
                Text(
                    text = text,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                )
                if (iconAttr.placeDirection == IconDirection.RIGHT) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(painter = painterResource(id = R.drawable.ic_edit_goal), contentDescription = "")
                }
            }
        }
    }
}

data class IconAttr(
    val painter: Painter,
    val placeDirection: IconDirection = IconDirection.RIGHT
)

enum class IconDirection {
    LEFT, RIGHT
}


@Preview(showBackground = true)
@Composable
fun PreviewRoundedRectText() {
    RoundedRectText(Modifier, "목표 재설정", 16.dp, IconAttr(painterResource(id = R.drawable.ic_edit_goal))) {}
}