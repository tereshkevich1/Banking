package com.example.banking.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE account_id = :accountId")
    fun getTransactions(accountId: Int): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE account_id = :accountId ORDER BY transaction_date DESC LIMIT :count")
    fun getLastTransactions(accountId: Int, count: Int): Flow<List<Transaction>>
}