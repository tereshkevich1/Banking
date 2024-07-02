package com.example.banking.data.data_source

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE account_id = :accountId")
    fun getTransactions(accountId: Int): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)
}