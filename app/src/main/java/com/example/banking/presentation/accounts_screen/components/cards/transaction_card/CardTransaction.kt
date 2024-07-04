package com.example.banking.presentation.accounts_screen.components.cards.transaction_card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banking.R
import com.example.banking.data.db.entity.Transaction
import com.example.banking.ui.theme.BankingTheme
import com.example.banking.util.CardState
import java.util.Date

@Composable
fun CardTransactions(
    transactions: List<Transaction>,
    onCardClick: (Transaction) -> Unit,
    maxVisibleItems: Int = Int.MAX_VALUE
) {
    val cardBackgroundColor = colorResource(id = R.color.account_card_background_color)
    val cardInnerPadding = dimensionResource(id = R.dimen.card_inner_padding)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        LazyColumn(modifier = Modifier) {
            items(transactions.take(maxVisibleItems)) { transaction ->
                Transaction(transaction) { onCardClick(transaction) }
                HorizontalDivider(modifier = Modifier.padding(horizontal = cardInnerPadding))
            }
        }
    }
}

@Composable
@Preview
fun CardTransactionPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            color = colorResource(id = R.color.black)
        ) {
            CardTransactions(
                listOf(
                    Transaction(1, 1, "Google", Date().time, 1000, CardState.EXECUTED, ""),
                    Transaction(1, 1, "Google", Date().time, 1000, CardState.DECLINED, ""),
                    Transaction(1, 1, "Google", Date().time, 1000, CardState.IN_PROGRESS, ""),
                    Transaction(1, 1, "Google", Date().time, 1000, CardState.EXECUTED, "")
                ), {}
            )
        }
    }
}