package com.example.banking.presentation.all_transactions_screen

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
import com.example.banking.presentation.accounts_screen.components.cards.transaction_card.CardTransactions
import com.example.banking.presentation.all_transactions_screen.components.NavTopPanel
import com.example.banking.presentation.all_transactions_screen.components.select_date_bottom_sheet.SelectDateBottomSheet
import com.example.banking.presentation.common.SharedTransactionViewModel
import com.example.banking.presentation.navigation.Screen
import com.example.banking.ui.theme.BankingTheme

@Composable
fun AllTransactionsScreen(
    allTransactionViewModel: AllTransactionViewModel = hiltViewModel(),
    sharedTransactionViewModel: SharedTransactionViewModel = hiltViewModel(),
    navController: NavController
) {
    val transactions by allTransactionViewModel.transactions.collectAsState()
    val startPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_start)
    val endPadding = dimensionResource(id = R.dimen.all_transactions_horizontal_padding_end)

    val backgroundColor = colorResource(id = R.color.surface_background_color)

    var showSheet by remember { mutableStateOf(false) }

    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = backgroundColor
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            if (showSheet) {
                SelectDateBottomSheet(
                    onSubmitButtonClick = { startDate, endDate ->
                        allTransactionViewModel.filterTransactionsByDate(startDate, endDate)
                        showSheet = false
                    },
                    onDismiss = { showSheet = false }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = startPadding, end = endPadding)
            ) {
                NavTopPanel(
                    onBackButtonClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            navController.popBackStack()
                        }
                    },
                    onOptionsButtonClick = { showSheet = true })
                CardTransactions(
                    transactions = transactions,
                    onCardClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            sharedTransactionViewModel.updateTransaction(it)
                            navController.navigate(Screen.CreateTransaction.route)
                        }
                    }
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