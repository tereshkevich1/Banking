package com.example.banking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banking.models.CardState
import com.example.banking.ui.theme.BankingTheme
import java.util.Date

@Composable
fun AccountsScreen() {

    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val containerColor = colorResource(id = R.color.floating_button_container_color)
    val contentColor = colorResource(id = R.color.white)
    val floatingButtonPadding = dimensionResource(id = R.dimen.floating_button_padding)

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
            cardName = "saving Account",
            accountNumber = "19124214302735",
            cardNumber = "12847234"
        ) {

        }
        RecentTransactionRow()
        CardTransactions(
            listOf(
                com.example.banking.models.Transaction("Google", Date(), 1000, CardState.EXECUTED),
                com.example.banking.models.Transaction("Google", Date(), 1000, CardState.DECLINED),
                com.example.banking.models.Transaction(
                    "Google",
                    Date(),
                    1000,
                    CardState.IN_PROGRESS
                ),
                com.example.banking.models.Transaction("Google", Date(), 1000, CardState.EXECUTED)
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = floatingButtonPadding),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { /*TODO*/ },
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