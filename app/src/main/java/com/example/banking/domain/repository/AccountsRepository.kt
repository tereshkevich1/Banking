package com.example.banking.domain.repository

import com.example.banking.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {
    fun getAccounts(): Flow<List<Account>>
    suspend fun getCurrentAccount(currentCard: Boolean): Account
    suspend fun insertAccount(account: Account)
    suspend fun updateAccount(account: Account)
    suspend fun insertAccountWithId(account: Account): Long
}