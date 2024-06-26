package com.example.banking.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.banking.R
import com.example.banking.accounts_screen.cards.account.CardAccount
import com.example.banking.models.Account

@Composable
fun AccountsList(
    accountsList: List<Account>,
    onAccountClick: (Account) -> Unit
) {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val textBottomPadding = dimensionResource(id = R.dimen.large_padding)
    Column(modifier = Modifier.padding(horizontal = innerPadding)) {
        Text(
            text = stringResource(id = R.string.accounts_bottom_sheet_title),
            modifier = Modifier.padding(bottom = textBottomPadding),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn {
            items(accountsList) { item: Account ->
                val cardBackgroundColor =
                    if (item.currentCard) colorResource(id = R.color.current_card_background_color)
                    else colorResource(id = R.color.account_card_background_color)
                CardAccount(
                    Modifier.padding(bottom = innerPadding),
                    cardBackgroundColor,
                    item,
                    onCardClick = onAccountClick
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}