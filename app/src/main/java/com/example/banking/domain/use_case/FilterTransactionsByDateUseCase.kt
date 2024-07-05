package com.example.banking.domain.use_case

import com.example.banking.data.db.entity.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class FilterTransactionsByDateUseCase @Inject constructor() {
    suspend operator fun invoke(
        startDate: Long,
        endDate: Long,
        transactionList: List<Transaction>
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            val startDateTime = Date(startDate)
            val endDateTime = Date(endDate)
            val calendar = Calendar.getInstance()

            calendar.time = startDateTime
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val adjustedStartDateTime = calendar.time

            calendar.time = endDateTime
            calendar.set(Calendar.HOUR_OF_DAY, 23)
            calendar.set(Calendar.MINUTE, 59)
            calendar.set(Calendar.SECOND, 59)
            calendar.set(Calendar.MILLISECOND, 999)
            val adjustedEndDateTime = calendar.time

            transactionList.filter {
                val transactionDateTime = Date(it.date)
                transactionDateTime in adjustedStartDateTime..adjustedEndDateTime
            }
        }
    }
}
