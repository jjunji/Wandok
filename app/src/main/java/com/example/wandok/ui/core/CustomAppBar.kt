package com.example.wandok.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wandok.R
import com.example.wandok.ui.theme.GrayC1

@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    val padding = 4.dp
    val density = LocalDensity.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .drawBehind {
                val paddingPx = with(density) { padding.toPx() }
                clipRect(top = size.height - paddingPx) {
                    val colors = listOf(Color.Transparent, GrayC1)
                    drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .clickable {
                    onBackClicked()
                },
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomAppBar() {
    CustomAppBar {}
}
