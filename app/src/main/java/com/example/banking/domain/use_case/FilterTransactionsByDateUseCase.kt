package com.example.banking.domain.use_case

import com.example.banking.data.db.entity.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FilterTransactionsByDateUseCase @Inject constructor() {
    suspend operator fun invoke(
        startDate: Long,
        endDate: Long,
        transactionList: List<Transaction>
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            val oneDayInMillis = TimeUnit.DAYS.toMillis(1)
            val startMillis = startDate - oneDayInMillis
            transactionList.filter { it.date in startMillis..endDate }
        }
    }
}