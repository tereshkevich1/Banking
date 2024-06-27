package com.example.banking.transcation_screen

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
import com.example.banking.R
import com.example.banking.ui.theme.BankingTheme

@Composable
fun TransactionsScreen(transactionViewModel: TransactionViewModel = viewModel()) {
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
            value = transactionViewModel.transactionApplied,
            onValueChange = { transactionViewModel.updateTransactionApplied(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = transactionNumberLabel,
            value = transactionViewModel.transactionNumber,
            onValueChange = { transactionViewModel.updateTransactionNumber(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = dateLabel,
            value = transactionViewModel.date,
            onValueChange = { transactionViewModel.updateDate(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = transactionStatusLabel,
            value = transactionViewModel.transactionStatus,
            onValueChange = { transactionViewModel.updateTransactionStatus(it) },
            modifier = Modifier
        )
        TextFieldWithLabel(
            label = amountLabel,
            value = transactionViewModel.amount,
            onValueChange = { transactionViewModel.updateAmount(it) },
            modifier = Modifier
        )

        OkButton(onClick = {}, modifier = Modifier.padding(top = innerPadding), transactionViewModel.isButtonEnable)
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
            TransactionsScreen()
        }
    }
}

