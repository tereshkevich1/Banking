package com.example.banking.domain.use_case

import com.example.banking.data.db.entity.Transaction
import com.example.banking.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertTransactionUseCase @Inject constructor(private val repository: TransactionRepository) {
    suspend operator fun invoke(transaction: Transaction) {
        repository.insertTransaction(transaction)
    }
}