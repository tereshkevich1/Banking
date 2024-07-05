package com.example.banking.presentation.create_transaction_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.data.db.entity.Transaction
import com.example.banking.domain.use_case.InsertTransactionUseCase
import com.example.banking.util.CardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(private val insertTransaction: InsertTransactionUseCase) :
    ViewModel() {
    var currentAccountId: Int? = null

    fun addTransaction(
        companyName: String,
        transactionNumber: String,
        amount: Long,
        state: CardState = CardState.IN_PROGRESS
    ) {
        val accountId = currentAccountId
        accountId?.let {
            viewModelScope.launch {
                val transaction = Transaction(
                    id = 0,
                    accountId = accountId,
                    companyName = companyName,
                    date = System.currentTimeMillis(),
                    amount = amount,
                    state = state,
                    transactionNumber = transactionNumber
                )
                insertTransaction(transaction)
            }
        }
    }
}