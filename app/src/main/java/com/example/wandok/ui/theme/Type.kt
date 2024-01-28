package com.example.wandok.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.R

private val notosansFamily = FontFamily(
    Font(R.font.notosanskr_regular, FontWeight.Normal),
    Font(R.font.notosanskr_medium, FontWeight.Medium),
    Font(R.font.notosanskr_bold, FontWeight.Bold),
    Font(R.font.notosanskr_light, FontWeight.Light)
)

// Set of Material typography styles to start with
private val baseTextStyle = TextStyle(
    fontFamily = notosansFamily,
)

// Set of Material typography styles to start with
val Typography = Typography(

)