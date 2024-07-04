package com.example.banking.presentation.accounts_screen.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.banking.R
import com.example.banking.data.data_source.Account
import com.example.banking.presentation.accounts_screen.cards.account.CardAccount

@Composable
fun AccountsList(
    accountsList: List<Account>,
    onAccountClick: (Account) -> Unit,
    account: Account
) {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val textBottomPadding = dimensionResource(id = R.dimen.large_padding)
    var currentAccount by remember { mutableStateOf(account) }

    Column(modifier = Modifier.padding(horizontal = innerPadding)) {
        Text(
            text = stringResource(id = R.string.accounts_bottom_sheet_title),
            modifier = Modifier.padding(bottom = textBottomPadding),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn {
            items(accountsList) { item: Account ->
                val cardBackgroundColor = getCardBackgroundColor(item, currentAccount)
                CardAccount(
                    Modifier.padding(bottom = innerPadding),
                    cardBackgroundColor,
                    item
                ) {
                    onAccountClick(item)
                    currentAccount = item
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

