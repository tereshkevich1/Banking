package com.example.banking

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.banking.models.Transaction
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TransactionDetails(
    transaction: Transaction
) {
    val stateColor = getColorByState(transaction.state)
    val textColor = Color.White.copy(alpha = 0.6f)
    val stateText = getStringByState(transaction.state)
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val formattedDate = dateFormat.format(transaction.date)

    Column {
        Text(
            text = transaction.companyName,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 20.sp
        )
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
        Text(
            text = stateText,
            style = MaterialTheme.typography.bodySmall,
            color = stateColor
        )
    }
}