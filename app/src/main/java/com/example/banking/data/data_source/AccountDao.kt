package com.example.banking.data.data_source

import androidx.room.Query
import kotlinx.coroutines.flow.Flow


interface AccountDao {
    @Query("SELECT * FROM accounts")
    fun getNotes(): Flow<List<Account>>

    @Query("SELECT * FROM accounts WHERE current_card = :currentCard")
    suspend fun getCurrentAccount(currentCard: Boolean): Account
}