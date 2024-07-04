package com.example.banking.presentation.accounts_screen.components.cards.transaction_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.data.db.entity.Transaction
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
            modifier = Modifier.defaultMinSize(minHeight = 28.dp),
            text = transaction.companyName,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.defaultMinSize(minHeight = 18.dp),
            text = formattedDate,
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
        Text(
            modifier = Modifier.defaultMinSize(minHeight = 18.dp),
            text = stateText,
            style = MaterialTheme.typography.bodySmall,
            color = stateColor
        )
    }
}