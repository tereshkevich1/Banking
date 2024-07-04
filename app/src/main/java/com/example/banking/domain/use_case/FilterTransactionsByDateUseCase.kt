package com.example.banking.domain.use_case

import com.example.banking.data.data_source.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilterTransactionsByDateUseCase @Inject constructor() {
    suspend operator fun invoke(
        startDate: Long,
        endDate: Long,
        transactionList: List<Transaction>
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            transactionList.filter { it.date in startDate..endDate }
        }
    }
}