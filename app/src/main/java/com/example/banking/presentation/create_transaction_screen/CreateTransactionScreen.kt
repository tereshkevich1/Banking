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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.banking.R
import com.example.banking.presentation.create_transaction_screen.components.TextFieldWithLabel
import com.example.banking.presentation.common.PrimaryButton
import com.example.banking.presentation.common.SharedTransactionViewModel
import com.example.banking.ui.theme.BankingTheme

@Composable
fun CreateTransactionsScreen(
    createTransactionViewModel: CreateTransactionViewModel = hiltViewModel(),
    transactionInputViewModel: TransactionInputViewModel = hiltViewModel(),
    sharedTransactionViewModel: SharedTransactionViewModel = hiltViewModel(),
    navController: NavController
) {
    val transaction = remember {
        sharedTransactionViewModel.getAndResetTransaction()
    }
    transactionInputViewModel.setUpFields(transaction)
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
                    value = transactionInputViewModel.transactionApplied,
                    onValueChange = { transactionInputViewModel.updateTransactionApplied(it) },
                    modifier = Modifier
                )
                TextFieldWithLabel(
                    label = transactionNumberLabel,
                    value = transactionInputViewModel.transactionNumber,
                    onValueChange = { transactionInputViewModel.updateTransactionNumber(it) },
                    modifier = Modifier
                )
                transaction?.let {
                    TextFieldWithLabel(
                        label = dateLabel,
                        value = transactionInputViewModel.date,
                        onValueChange = { transactionInputViewModel.updateDate(it) },
                        modifier = Modifier
                    )
                }
                TextFieldWithLabel(
                    label = transactionStatusLabel,
                    value = transactionInputViewModel.transactionStatus,
                    onValueChange = { transactionInputViewModel.updateTransactionStatus(it) },
                    modifier = Modifier
                )
                TextFieldWithLabel(
                    label = amountLabel,
                    value = transactionInputViewModel.amount,
                    onValueChange = { transactionInputViewModel.updateAmount(it) },
                    modifier = Modifier,
                    keyboardType = KeyboardType.Number
                )

                PrimaryButton(
                    onClick = {
                        val currentState = lifecycleOwner.lifecycle.currentState
                        if (currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                            transaction ?: run {
                                createTransactionViewModel.addTransaction(
                                    transactionInputViewModel.transactionApplied,
                                    transactionInputViewModel.transactionNumber,
                                    transactionInputViewModel.amount.toLong()
                                )
                            }
                            navController.popBackStack()
                        }
                    },
                    buttonText = stringResource(id = R.string.ok),
                    modifier = Modifier.padding(top = innerPadding),
                    transactionInputViewModel.isButtonEnable
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

