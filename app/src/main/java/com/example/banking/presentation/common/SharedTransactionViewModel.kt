package com.example.banking.presentation.common

import androidx.lifecycle.ViewModel
import com.example.banking.data.db.entity.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedTransactionViewModel @Inject constructor() : ViewModel() {
    private var transaction: Transaction? = null

    fun getAndResetTransaction(): Transaction? {
        val tempTransaction = transaction
        transaction = null
        return tempTransaction
    }

    fun updateTransaction(setUpTransaction: Transaction){
        transaction = setUpTransaction
    }
}