package com.example.banking.all_transactions_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.banking.R
import com.example.banking.accounts_screen.cards.transaction_card.CardTransactions
import com.example.banking.ui.theme.BankingTheme

@Composable
fun AllTransactionsScreen(transactionsViewModel: TransactionViewModel = viewModel()) {
    transactionsViewModel.loadTransactions()
    val transactions by transactionsViewModel.transactions.collectAsState()
    val startPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_start)
    val endPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_end)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = startPadding, end = endPadding)
    ) {
        NavTopPanel({},{})
        CardTransactions(
            transactions
        )
    }
}

@Composable
@Preview
fun TransactionScreenPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            AllTransactionsScreen()
        }
    }
}