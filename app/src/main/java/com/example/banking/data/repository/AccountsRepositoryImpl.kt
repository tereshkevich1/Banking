package com.example.banking.data.repository

import com.example.banking.data.data_source.Account
import com.example.banking.data.data_source.AccountDao
import kotlinx.coroutines.flow.Flow

class AccountsRepositoryImpl(private val accountDao: AccountDao) : AccountsRepository {
    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getNotes()
    }

    override suspend fun getCurrentAccount(currentCard: Boolean): Account {
        return accountDao.getCurrentAccount(currentCard)
    }
}