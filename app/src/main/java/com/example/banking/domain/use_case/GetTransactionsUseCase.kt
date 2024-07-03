package com.example.banking.domain.use_case

import com.example.banking.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(private val repository: TransactionRepository) {
    operator fun invoke(accountId: Int) = repository.getTransactions(accountId)
        .map { transactions ->
            transactions.sortedByDescending { it.date }
        }
}