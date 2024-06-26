package com.example.banking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun AccountDetails(
    cardName: String,
    accountNumber: String,
    cardNumber: String,
    textColor: Color,
    bottomTextCardPadding: Dp
) {
    Column {
        Text(
            text = cardName,
            modifier = Modifier.padding(bottom = bottomTextCardPadding),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 20.sp
        )
        Text(
            text = accountNumber,
            modifier = Modifier.padding(bottom = bottomTextCardPadding),
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
        Text(
            text = "•••• ${cardNumber.takeLast(4)}",
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
    }
}