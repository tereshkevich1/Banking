package com.example.banking.domain.use_case

import com.example.banking.domain.repository.TransactionRepository
import javax.inject.Inject

class GetLastTransactionsUseCase @Inject constructor(private val repository: TransactionRepository) {
    suspend operator fun invoke(accountId: Int, count: Int) =
        repository.getLastTransactions(accountId, count)
}