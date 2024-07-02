package com.example.banking.data.repository

import com.example.banking.data.data_source.Account
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {
    fun getAccounts(): Flow<List<Account>>
    suspend fun getCurrentAccount(currentCard: Boolean): Account
}