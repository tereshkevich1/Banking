package com.example.banking.presentation.accounts_screen.components.cards.transaction_card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.banking.R
import com.example.banking.data.db.entity.Transaction
import com.example.banking.presentation.accounts_screen.components.cards.ChevronForwardIcon


@Composable
fun Transaction(transaction: Transaction, onCardClick: (Transaction) -> Unit) {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val amount = "$${transaction.amount}"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick(transaction) }
            .padding(innerPadding),
        verticalAlignment = Alignment.Top
    ) {
        TransactionDetails(transaction)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = amount,
            modifier = Modifier
                .padding(end = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Thin
        )
        ChevronForwardIcon(modifier = Modifier.padding(top = 2.dp))
    }
}