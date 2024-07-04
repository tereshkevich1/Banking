package com.example.banking.data.repository_impl

import com.example.banking.data.data_source.Transaction
import com.example.banking.data.data_source.TransactionDao
import com.example.banking.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override fun getTransactions(accountId: Int): Flow<List<Transaction>> {
        return transactionDao.getTransactions(accountId)
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        return transactionDao.insertTransaction(transaction)
    }
}