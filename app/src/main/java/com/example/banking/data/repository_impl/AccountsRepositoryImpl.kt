package com.example.banking.data.repository_impl

import com.example.banking.data.data_source.Account
import com.example.banking.data.data_source.AccountDao
import com.example.banking.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow

class AccountsRepositoryImpl(private val accountDao: AccountDao) : AccountsRepository {
    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getNotes()
    }

    override suspend fun getCurrentAccount(currentCard: Boolean): Account {
        return accountDao.getCurrentAccount(currentCard)
    }
}