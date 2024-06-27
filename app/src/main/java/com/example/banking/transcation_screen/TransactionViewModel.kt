package com.example.banking.transcation_screen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TransactionViewModel : ViewModel() {
    val isButtonEnable by derivedStateOf {
        transactionApplied.isNotEmpty() &&
                transactionNumber.isNotEmpty() &&
                date.isNotEmpty() &&
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
}