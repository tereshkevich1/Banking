package com.example.banking.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.banking.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts")
    fun getNotes(): Flow<List<Account>>

    @Query("SELECT * FROM accounts WHERE current_card = :currentCard")
    suspend fun getCurrentAccount(currentCard: Boolean): Account

    @Insert
    suspend fun insertAccount(account: Account)

    @Insert
    suspend fun insertAccountWithId(account: Account): Long

    @Update
    suspend fun updateAccount(account: Account)
}