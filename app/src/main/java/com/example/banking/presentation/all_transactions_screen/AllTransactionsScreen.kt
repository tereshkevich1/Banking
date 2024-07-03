package com.example.banking.presentation.all_transactions_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.banking.R
import com.example.banking.presentation.accounts_screen.AccountsViewModel
import com.example.banking.presentation.accounts_screen.cards.transaction_card.CardTransactions
import com.example.banking.presentation.all_transactions_screen.select_date_bottom_sheet.SelectDateBottomSheet
import com.example.banking.ui.theme.BankingTheme

@Composable
fun AllTransactionsScreen(
    accountsViewModel: AccountsViewModel = hiltViewModel(),
    navController: NavController
) {
    val transactions by accountsViewModel.transactions.collectAsState()
    val startPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_start)
    val endPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_end)

    val backgroundColor = colorResource(id = R.color.surface_background_color)

    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
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
                val lifecycleOwner = LocalLifecycleOwner.current
                NavTopPanel(
                    onBackButtonClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            navController.popBackStack()
                        }
                    },
                    onOptionsButtonClick = { showSheet = true })
                CardTransactions(
                    transactions
                )
            }
        }
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
            //AllTransactionsScreen(navController = rememberNavController(), )
        }
    }
}