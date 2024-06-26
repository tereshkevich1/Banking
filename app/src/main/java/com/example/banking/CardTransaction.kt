package com.example.banking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.models.CardState
import com.example.banking.models.Transaction
import com.example.banking.ui.theme.BankingTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CardTransactions(
    transactions: List<Transaction>
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
        LazyColumn {
            items(transactions) { transaction ->
                Transaction(transaction)
                HorizontalDivider(modifier = Modifier.padding(horizontal = cardInnerPadding))
            }
        }
    }
}

@Composable
fun Transaction(transaction: Transaction) {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val amount = "$${transaction.amount}"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(innerPadding),
        verticalAlignment = Alignment.Top
    ) {
        TransactionDetails(transaction)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = amount,
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
        ChevronForwardIcon()
    }
}


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

@Composable
fun getColorByState(state: CardState): Color {
    return when (state) {
        CardState.EXECUTED -> colorResource(id = R.color.executed_color)
        CardState.DECLINED -> colorResource(id = R.color.declined_color)
        CardState.IN_PROGRESS -> colorResource(id = R.color.in_progress_color)
    }
}

@Composable
fun getStringByState(state: CardState): String {
    return when (state) {
        CardState.EXECUTED -> stringResource(id = R.string.executed)
        CardState.DECLINED -> stringResource(id = R.string.declined)
        CardState.IN_PROGRESS -> stringResource(id = R.string.in_progress_)
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
                    Transaction("Google", Date(), 1000, CardState.EXECUTED),
                    Transaction("Google", Date(), 1000, CardState.DECLINED),
                    Transaction("Google", Date(), 1000, CardState.IN_PROGRESS),
                    Transaction("Google", Date(), 1000, CardState.EXECUTED)
                )
            )
        }
    }
}