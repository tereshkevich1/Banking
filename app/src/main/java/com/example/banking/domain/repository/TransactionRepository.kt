package com.example.banking.domain.repository

import com.example.banking.data.data_source.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(accountId: Int): Flow<List<Transaction>>
    suspend fun insertTransaction(transaction: Transaction)
}