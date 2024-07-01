package com.example.banking.create_transaction_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.banking.R
import com.example.banking.ui.theme.BankingTheme

@Composable
fun CreateTransactionsScreen(
    createTransactionViewModel: CreateTransactionViewModel = viewModel(),
    navController: NavController
) {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val bottomTextPadding = dimensionResource(id = R.dimen.large_padding)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {

        Text(
            text = stringResource(id = R.string.transaction),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = bottomTextPadding)
        )
        val transactionAppliedLabel = stringResource(id = R.string.transaction_applied)
        val transactionNumberLabel = stringResource(id = R.string.transaction_number)
        val dateLabel = stringResource(id = R.string.date)
        val transactionStatusLabel = stringResource(id = R.string.transaction_status)
        val amountLabel = stringResource(id = R.string.amount)

        TextFieldWithLabel(
            label = transactionAppliedLabel,
            value = createTransactionViewModel.transactionApplied,
            onValueChange = { createTransactionViewModel.updateTransactionApplied(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = transactionNumberLabel,
            value = createTransactionViewModel.transactionNumber,
            onValueChange = { createTransactionViewModel.updateTransactionNumber(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = dateLabel,
            value = createTransactionViewModel.date,
            onValueChange = { createTransactionViewModel.updateDate(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = transactionStatusLabel,
            value = createTransactionViewModel.transactionStatus,
            onValueChange = { createTransactionViewModel.updateTransactionStatus(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = amountLabel,
            value = createTransactionViewModel.amount,
            onValueChange = { createTransactionViewModel.updateAmount(it) },
            modifier = Modifier
        )

        OkButton(
            onClick = {},
            modifier = Modifier.padding(top = innerPadding),
            createTransactionViewModel.isButtonEnable
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
            CreateTransactionsScreen(navController = rememberNavController())
        }
    }
}

