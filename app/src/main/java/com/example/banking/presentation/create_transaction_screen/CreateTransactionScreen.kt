package com.example.banking.presentation.create_transaction_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.banking.presentation.common_vm.AccountsViewModel
import com.example.banking.presentation.common_vm.SharedTransactionViewModel
import com.example.banking.ui.theme.BankingTheme

@Composable
fun CreateTransactionsScreen(
    accountsViewModel: AccountsViewModel = hiltViewModel(),
    createTransactionViewModel: CreateTransactionViewModel = hiltViewModel(),
    sharedTransactionViewModel: SharedTransactionViewModel = hiltViewModel(),
    navController: NavController
) {
    val transaction = remember {
        sharedTransactionViewModel.getAndResetTransaction()
    }
    createTransactionViewModel.setUpFields(transaction)
    val lifecycleOwner = LocalLifecycleOwner.current

    val transactionAppliedLabel = stringResource(id = R.string.transaction_applied)
    val transactionNumberLabel = stringResource(id = R.string.transaction_number)
    val dateLabel = stringResource(id = R.string.date)
    val transactionStatusLabel = stringResource(id = R.string.transaction_status)
    val amountLabel = stringResource(id = R.string.amount)

    val innerPadding = dimensionResource(id = R.dimen.inner_padding)
    val bottomTextPadding = dimensionResource(id = R.dimen.large_padding)

    val backgroundColor = colorResource(id = R.color.surface_background_color)

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
                transaction?.let {
                    TextFieldWithLabel(
                        label = dateLabel,
                        value = createTransactionViewModel.date,
                        onValueChange = { createTransactionViewModel.updateDate(it) },
                        modifier = Modifier
                    )
                }
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
                    onClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            transaction ?: run {
                                accountsViewModel.addTransaction(
                                    createTransactionViewModel.transactionApplied,
                                    createTransactionViewModel.transactionNumber,
                                    createTransactionViewModel.amount.toLong()
                                )
                            }
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.padding(top = innerPadding),
                    createTransactionViewModel.isButtonEnable
                )
            }
        }
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
            //CreateTransactionsScreen(navController = rememberNavController())
        }
    }
}

