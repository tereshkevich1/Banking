package com.example.banking.domain.use_case

import com.example.banking.data.data_source.Transaction
import com.example.banking.data.repository.TransactionRepository

class InsertTransactionUseCase(private val repository: TransactionRepository) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.insertTransaction(transaction)
    }
}