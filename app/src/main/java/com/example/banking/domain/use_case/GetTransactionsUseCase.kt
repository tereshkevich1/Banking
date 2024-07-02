package com.example.banking.domain.use_case

import com.example.banking.domain.repository.TransactionRepository

class GetTransactionsUseCase(private val repository: TransactionRepository) {
    operator fun invoke(accountId: Int) = repository.getTransactions(accountId)
}