@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.banking.accounts_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.banking.R
import com.example.banking.accounts_screen.bottomSheet.AccountsBottomSheet
import com.example.banking.accounts_screen.cards.account.CardAccount
import com.example.banking.accounts_screen.cards.transaction_card.CardTransactions
import com.example.banking.accounts_screen.cards.transaction_card.RecentTransactionRow
import com.example.banking.models.Account
import com.example.banking.models.CardState
import com.example.banking.models.Transaction
import com.example.banking.ui.theme.BankingTheme
import java.util.Date

@ExperimentalMaterial3Api
@Composable
fun AccountsScreen(accountsViewModel: AccountsViewModel = viewModel()) {

    val currentAccount by accountsViewModel.account.collectAsState()
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val containerColor = colorResource(id = R.color.floating_button_container_color)
    val contentColor = colorResource(id = R.color.white)
    val floatingButtonPadding = dimensionResource(id = R.dimen.floating_button_padding)
    val backgroundCardColor = colorResource(id = R.color.account_card_background_color)

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        AccountsBottomSheet(
            listOf(
                Account("saving Account", "19124214302735", "12847234", true),
                Account("sav Account", "19124214302735", "12847230", false),
                Account("sa Account", "19124214302735", "12847241", false),
                Account("s Account", "19124214302735", "12847342", false),
            ),
            onDismiss = { showSheet = false },
            onAccountClick = { account -> accountsViewModel.updateAccount(account) },
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
        RecentTransactionRow()
        CardTransactions(
            listOf(
                Transaction("Google", Date(), 1000, CardState.EXECUTED),
                Transaction("Google", Date(), 1000, CardState.DECLINED),
                Transaction(
                    "Google",
                    Date(),
                    1000,
                    CardState.IN_PROGRESS
                ),
                Transaction("Google", Date(), 1000, CardState.EXECUTED)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = floatingButtonPadding),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                containerColor = containerColor,
                contentColor = contentColor
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "plus"
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
            AccountsScreen()
        }
    }
}