package com.example.wandok.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.wandok.R
import com.example.wandok.ui.theme.Orange300

@Composable
fun ConfirmCancelDialog(
    title: String,
    description: String = "",
    positiveText: String = stringResource(id = R.string.common_confirm),
    negativeText: String = stringResource(id = R.string.common_cancel),
    onConfirmClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = { onCancelClicked() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 24.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Body1Text(
                text = title,
                textAlign = TextAlign.Center,
                bold = true
            )
            if (description.isNotBlank()) {
                Spacer(modifier = Modifier.size(4.dp))
                Body2Text(
                    text = description,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.size(25.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Body1Text(
                    text = negativeText,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onCancelClicked() },
                    color = Orange300,
                    bold = true
                )
                Body1Text(
                    text = positiveText,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onConfirmClicked() },
                    color = Orange300,
                    bold = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    ConfirmCancelDialog(
        title = "제목",
        positiveText = "확인",
        negativeText = "취소",
        onConfirmClicked = {},
        onCancelClicked = {}
    )
}