package com.example.banking.domain.repository

import com.example.banking.data.db.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(accountId: Int): Flow<List<Transaction>>
    suspend fun insertTransaction(transaction: Transaction)
    suspend fun getLastTransactions(accountId: Int, count: Int): Flow<List<Transaction>>
}