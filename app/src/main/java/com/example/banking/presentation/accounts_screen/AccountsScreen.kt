@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.banking.presentation.accounts_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.banking.R
import com.example.banking.presentation.accounts_screen.components.FloatingActionButtonBox
import com.example.banking.presentation.accounts_screen.components.bottomSheet.AccountsBottomSheet
import com.example.banking.presentation.accounts_screen.components.cards.account.CardAccount
import com.example.banking.presentation.accounts_screen.components.cards.transaction_card.CardTransactions
import com.example.banking.presentation.accounts_screen.components.cards.transaction_card.RecentTransactionRow
import com.example.banking.presentation.navigation.Screen
import com.example.banking.presentation.common.SharedTransactionViewModel
import com.example.banking.presentation.navigation.ARG_ACCOUNT_ID
import com.example.banking.ui.theme.BankingTheme

@ExperimentalMaterial3Api
@Composable
fun AccountsScreen(
    accountsViewModel: AccountsViewModel = hiltViewModel(),
    sharedTransactionViewModel: SharedTransactionViewModel = hiltViewModel(),
    navController: NavController
) {
    val currentAccount by accountsViewModel.currentAccount.collectAsState()
    val accounts by accountsViewModel.accounts.collectAsState()

    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val backgroundColor = colorResource(id = R.color.surface_background_color)

    val containerColor = colorResource(id = R.color.floating_button_container_color)
    val contentColor = colorResource(id = R.color.white)
    val floatingButtonPadding = dimensionResource(id = R.dimen.floating_button_padding)
    val backgroundCardColor = colorResource(id = R.color.account_card_background_color)

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
                AccountsBottomSheet(
                    accountsList = accounts,
                    onDismiss = { showSheet = false },
                    onAccountClick = { account -> accountsViewModel.changeCurrentAccount(account) },
                    account = currentAccount
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = stringResource(id = R.string.accounts),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = innerPadding)
                )
                CardAccount(
                    Modifier,
                    backgroundCardColor,
                    currentAccount
                ) { showSheet = true }

                RecentTransactionRow {
                    val currentState = lifecycleOwner.lifecycle.currentState
                    if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                        navController.navigate(
                            Screen.AllTransactions.route.replace(
                                "{$ARG_ACCOUNT_ID}",
                                currentAccount.id.toString()
                            )
                        )
                    }
                }

                CardTransactions(
                    transactions = accountsViewModel.lastTransactions.collectAsState().value,
                    onCardClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            sharedTransactionViewModel.updateTransaction(it)
                            navController.navigate(Screen.CreateTransaction.route)
                        }
                    },
                    maxVisibleItems = 4
                )

                FloatingActionButtonBox(
                    containerColor = containerColor,
                    contentColor = contentColor,
                    floatingButtonPadding = floatingButtonPadding,
                    onClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            navController.navigate(
                                Screen.CreateTransaction.route.replace(
                                    "{$ARG_ACCOUNT_ID}",
                                    currentAccount.id.toString()
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}


@Composable
@Preview
fun AccountsScreenPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            //  AccountsScreen(navController = rememberNavController())
        }
    }
}