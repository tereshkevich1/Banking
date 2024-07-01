package com.example.banking.all_transactions_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banking.R
import com.example.banking.accounts_screen.cards.transaction_card.CardTransactions
import com.example.banking.all_transactions_screen.select_date_bottom_sheet.SelectDateBottomSheet
import com.example.banking.ui.theme.BankingTheme

@Composable
fun AllTransactionsScreen(
    transactionsViewModel: TransactionViewModel = viewModel(),
    navController: NavController
) {
    transactionsViewModel.loadTransactions()
    val transactions by transactionsViewModel.transactions.collectAsState()
    val startPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_start)
    val endPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_end)

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        SelectDateBottomSheet(
            onSubmitButtonClick = { showSheet = false },
            onDismiss = { showSheet = false }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = startPadding, end = endPadding)
    ) {
        NavTopPanel(
            onBackButtonClick = { navController.popBackStack() },
            onOptionsButtonClick = { showSheet = true })
        CardTransactions(
            transactions
        )
    }
}

@Composable
@Preview
fun AllTransactionScreenPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            AllTransactionsScreen(navController = rememberNavController())
        }
    }
}