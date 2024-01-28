package com.example.wandok.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.wandok.ui.theme.GrayC1

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    TextField(
        placeholder = {
            Text(text = hint, color = GrayC1)
        },
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = GrayC1
        ),
        modifier = modifier.fillMaxWidth()
    )
}