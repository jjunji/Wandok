package com.example.wandok.ui.core

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wandok.ui.theme.LightGray

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 10.sp,
    hint: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        textStyle = TextStyle.Default.copy(fontSize = textSize),
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = hint,
                        fontSize = textSize,
                        color = LightGray
                    )
                },
                singleLine = true,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    top = 2.dp, bottom = 2.dp, start = 0.dp, end = 0.dp
                )
            )
        }
    )
}