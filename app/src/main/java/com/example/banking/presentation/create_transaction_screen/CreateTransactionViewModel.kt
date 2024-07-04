package com.example.banking.presentation.create_transaction_screen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.banking.data.data_source.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTransactionViewModel @Inject constructor() :
    ViewModel() {
    val isButtonEnable by derivedStateOf {
        transactionApplied.isNotEmpty() &&
                transactionNumber.isNotEmpty() &&
                transactionStatus.isNotEmpty() &&
                amount.isNotEmpty()
    }

    var transactionApplied by mutableStateOf("")
        private set

    var transactionNumber by mutableStateOf("")
        private set

    var date by mutableStateOf("")
        private set

    var transactionStatus by mutableStateOf("")
        private set

    var amount by mutableStateOf("")
        private set

    fun updateTransactionApplied(input: String) {
        transactionApplied = input
    }

    fun updateTransactionNumber(input: String) {
        transactionNumber = input
    }

    fun updateDate(input: String) {
        date = input
    }

    fun updateTransactionStatus(input: String) {
        transactionStatus = input
    }

    fun updateAmount(input: String) {
        amount = input
    }

    fun setUpFields(transactionToSetUp: Transaction?) {
        transactionToSetUp?.let {
            transactionApplied = transactionToSetUp.companyName
            transactionNumber = transactionToSetUp.transactionNumber
            date = transactionToSetUp.date.toString()
            transactionStatus = transactionToSetUp.state.toString()
            amount = transactionToSetUp.amount.toString()
        }
    }
}